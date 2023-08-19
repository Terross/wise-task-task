package ru.leti.wise.task.task.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.task.TaskGrpc.GetAllTaskSolutionsRequest;
import ru.leti.wise.task.task.TaskGrpc.GetAllTaskSolutionsResponse;
import ru.leti.wise.task.task.mapper.SolutionMapper;
import ru.leti.wise.task.task.repository.SolutionRepository;

import static java.util.UUID.fromString;

@Component
@RequiredArgsConstructor
public class GetTaskSolutionsOperation {

    private final SolutionMapper solutionMapper;
    private final SolutionRepository solutionRepository;

    public GetAllTaskSolutionsResponse activate(GetAllTaskSolutionsRequest request) {
        var solutions = solutionRepository.findSolutionEntitiesByAuthorIdAndTaskId(fromString(request.getAuthorId()),
                fromString(request.getTaskId()));

        return GetAllTaskSolutionsResponse.newBuilder()
                .addAllSolution(solutionMapper.toSolutions(solutions))
                .build();
    }
}
