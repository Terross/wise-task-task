package ru.leti.wise.task.task.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.graph.GraphOuterClass;
import ru.leti.wise.task.plugin.PluginOuterClass;
import ru.leti.wise.task.task.TaskGrpc.SolveTaskRequest;
import ru.leti.wise.task.task.TaskGrpc.SolveTaskResponse;
import ru.leti.wise.task.task.TaskOuterClass;
import ru.leti.wise.task.task.error.BusinessException;
import ru.leti.wise.task.task.error.ErrorCode;
import ru.leti.wise.task.task.mapper.SolutionMapper;
import ru.leti.wise.task.task.model.Answer;
import ru.leti.wise.task.task.model.Condition;
import ru.leti.wise.task.task.model.PluginResultEntity;
import ru.leti.wise.task.task.repository.SolutionRepository;
import ru.leti.wise.task.task.repository.TaskRepository;
import ru.leti.wise.task.task.service.plugin.PluginGrpcService;

import java.util.UUID;

import static java.util.UUID.fromString;
import static java.util.UUID.randomUUID;
import static ru.leti.wise.task.task.error.ErrorCode.TASK_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class SolveTaskOperation {

    private final TaskRepository taskRepository;
    private final SolutionMapper solutionMapper;
    private final PluginGrpcService pluginGrpcService;
    private final SolutionRepository solutionRepository;

    public SolveTaskResponse activate(SolveTaskRequest request) {
        var graph = request.getSolution().getGraph();
        var solution = solutionMapper.toSolution(request.getSolution());
        var task = taskRepository.findById(solution.getTaskId())
                .orElseThrow(() -> new BusinessException(TASK_NOT_FOUND));

        var pluginResults = task.getConditionList().stream()
                .map(condition -> getPluginResult(condition, graph))
                .toList();

        solution.setPluginResultEntities(pluginResults);

        boolean isCorrect = true;
        for (PluginResultEntity pluginResult: solution.getPluginResultEntities()) {
            if (!pluginResult.isCorrect()) {
                isCorrect = false;
            }
        }

        solution.setCorrect(isCorrect);

        solutionRepository.save(solution);
        return null;
    }

    private PluginResultEntity getPluginResult(Condition condition, GraphOuterClass.Graph graph) {
        var response = pluginGrpcService.checkPluginSolution(buildPluginSolution(condition, graph));
        var isCorrect = response.equals(condition.getAnswer().getValue());
        var answer = condition.getAnswer();
        return PluginResultEntity.builder()
                .id(randomUUID())
                .pluginId(condition.getPluginId())
                .isCorrect(isCorrect)
                .exceptedAnswer(answer)
                .answer(Answer.builder()
                        .id(randomUUID())
                        .pluginType(answer.getPluginType())
                        .value(response)
                        .build())
                .build();
    }

    private PluginOuterClass.Solution buildPluginSolution(Condition condition, GraphOuterClass.Graph graph) {
        return PluginOuterClass.Solution.newBuilder()
                .setPluginId(condition.getPluginId().toString())
                .setPluginType(PluginOuterClass.PluginType
                        .valueOf(condition.getAnswer().getPluginType().toString()))
                .setGraph(graph)
                .build();
    }
}
