package ru.leti.wise.task.task.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.task.TaskGrpc;
import ru.leti.wise.task.task.error.BusinessException;
import ru.leti.wise.task.task.mapper.SolutionMapper;
import ru.leti.wise.task.task.model.solution.SolutionGraph;
import ru.leti.wise.task.task.repository.SolutionRepository;
import ru.leti.wise.task.task.service.grpc.graph.GraphGrpcService;

import static java.util.UUID.fromString;
import static ru.leti.wise.task.task.error.ErrorCode.SOLUTION_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class GetTaskSolutionOperation {

    private final SolutionMapper solutionMapper;
    private final SolutionRepository solutionRepository;

    public TaskGrpc.GetTaskSolutionResponse activate(TaskGrpc.GetTaskSolutionRequest request) {
        var solution = solutionRepository.findById(fromString(request.getId()))
                .orElseThrow(() -> new BusinessException(SOLUTION_NOT_FOUND));
        return TaskGrpc.GetTaskSolutionResponse.newBuilder()
                .setSolution(solutionMapper.toSolution(solution))
                .build();
    }
}
