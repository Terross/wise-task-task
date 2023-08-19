package ru.leti.wise.task.task.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.task.TaskGrpc.GetTaskSolutionRequest;
import ru.leti.wise.task.task.TaskGrpc.GetTaskSolutionResponse;
import ru.leti.wise.task.task.error.BusinessException;
import ru.leti.wise.task.task.mapper.SolutionMapper;
import ru.leti.wise.task.task.repository.SolutionRepository;
import ru.leti.wise.task.task.service.graph.GraphGrpcService;

import static java.util.UUID.fromString;
import static ru.leti.wise.task.task.error.ErrorCode.SOLUTION_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class GetTaskSolutionOperation {

    private final SolutionMapper solutionMapper;
    private final GraphGrpcService graphGrpcService;
    private final SolutionRepository solutionRepository;

    public GetTaskSolutionResponse activate(GetTaskSolutionRequest request) {
        var solution = solutionRepository.findById(fromString(request.getId()))
                .orElseThrow(() -> new BusinessException(SOLUTION_NOT_FOUND));
        var graph = graphGrpcService.getGraphById(solution.getGraphId());

        return GetTaskSolutionResponse.newBuilder()
                .setSolution(solutionMapper.toSolutionWithGraph(solution, graph))
                .build();
    }
}
