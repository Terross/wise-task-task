package ru.leti.wise.task.task.service.task;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.graph.GraphOuterClass;
import ru.leti.wise.task.plugin.PluginOuterClass;
import ru.leti.wise.task.task.TaskGrpc;
import ru.leti.wise.task.task.error.BusinessException;
import ru.leti.wise.task.task.mapper.SolutionMapper;
import ru.leti.wise.task.task.model.solution.SolutionGraph;
import ru.leti.wise.task.task.model.task.PluginType;
import ru.leti.wise.task.task.model.task.TaskGraph;
import ru.leti.wise.task.task.repository.SolutionRepository;
import ru.leti.wise.task.task.repository.TaskRepository;
import ru.leti.wise.task.task.service.grpc.graph.GraphGrpcService;
import ru.leti.wise.task.task.service.grpc.plugin.PluginGrpcService;

import java.util.HashMap;
import java.util.UUID;

import static ru.leti.wise.task.task.error.ErrorCode.TASK_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class TaskGraphService {

    private static final String TRUE_PROPERTY = "Выполняется";
    private static final String FALSE_PROPERTY = "Не выполняется";

    private final TaskRepository taskRepository;
    private final SolutionMapper solutionMapper;
    private final GraphGrpcService graphGrpcService;
    private final SolutionRepository solutionRepository;
    private final PluginGrpcService pluginGrpcService;

    public TaskGrpc.SolveTaskResponse process(TaskGrpc.SolveTaskRequest request) {
        SolutionGraph solution = solutionMapper.toSolutionGraph(request.getSolution());
        TaskGraph task = (TaskGraph) taskRepository.findById(solution.getTaskId())
                .orElseThrow(() -> new BusinessException(TASK_NOT_FOUND));
        var graph = request.getSolution().getSolutionGraph().getGraph();
        HashMap<UUID, String> handWrittenAnswer = new HashMap<>();
        request.getSolution().getSolutionGraph().getPluginStringResultList()
                .stream()
                .map(pluginStringInput -> handWrittenAnswer
                        .put(UUID.fromString(pluginStringInput.getPluginId()), pluginStringInput.getHandWrittenAnswer()));

        graphGrpcService.createGraph(graph);
        var pluginResults = task.getCondition()
                .stream()
                .map(condition -> handWrittenAnswer.containsKey(condition.getPluginId()) ?
                        getPluginResult(condition, graph, handWrittenAnswer.get(condition.getPluginId()))
                        : getPluginResult(condition, graph, ""))
                .toList();

        solution.setResult(pluginResults);
        boolean isCorrect = true;
        for (SolutionGraph.PluginResult pluginResult : solution.getResult()) {
            if (!pluginResult.getIsCorrect()) {
                isCorrect = false;
                break;
            }
        }
        solution.setIsCorrect(isCorrect);
        solutionRepository.save(solution);
        return TaskGrpc.SolveTaskResponse.newBuilder()
                .setSolution(solutionMapper.toSolution(solution))
                .build();
    }

    private SolutionGraph.PluginResult getPluginResult(TaskGraph.PluginInfo pluginInfo, GraphOuterClass.Graph graph, String handWrittenAnswer) {
        var response = pluginGrpcService.checkPluginSolution(buildPluginSolution(pluginInfo, graph, handWrittenAnswer));
        var isCorrect = false;
        if (pluginInfo.getPluginType() == PluginType.GRAPH_CHARACTERISTIC) {
            isCorrect = validateCharacteristic(
                    Integer.parseInt(response),
                    Integer.parseInt(pluginInfo.getValue()),
                    pluginInfo.getSign());
        } else {
            if (response.equals("true") && TRUE_PROPERTY.equals(pluginInfo.getValue()) ||
                response.equals("false") && FALSE_PROPERTY.equals(pluginInfo.getValue())) {
                isCorrect = true;
            }
        }
        return SolutionGraph.PluginResult.builder()
                .pluginId(pluginInfo.getPluginId())
                .isCorrect(isCorrect)
                .trueValue(pluginInfo.getValue())
                .value(response)
                .pluginMessage(pluginInfo.getMistakeText())
                .build();
    }

    private PluginOuterClass.Solution buildPluginSolution(TaskGraph.PluginInfo pluginInfo, GraphOuterClass.Graph graph, String handWrittenAnswer) {
        return PluginOuterClass.Solution.newBuilder()
                .setPluginId(pluginInfo.getPluginId().toString())
                .setPluginType(PluginOuterClass.PluginType.valueOf(pluginInfo.getPluginType().toString()))
                .setHandwrittenAnswer(handWrittenAnswer)
                .setGraph(graph)
                .build();
    }

    private Boolean validateCharacteristic(Integer response, Integer value, String sign) {
        return switch (sign) {
            case ">" -> response > value;
            case "<" -> response < value;
            case "=" -> response.equals(value);
            case ">=" -> response >= value;
            case "<=" -> response <= value;
            default -> false;
        };
    }
}
