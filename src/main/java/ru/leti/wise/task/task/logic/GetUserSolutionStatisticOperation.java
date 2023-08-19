package ru.leti.wise.task.task.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.task.TaskGrpc;
import ru.leti.wise.task.task.TaskGrpc.GetUserSolutionStatisticResponse;
import ru.leti.wise.task.task.TaskGrpc.GetUserSolutionStatisticRequest;
import ru.leti.wise.task.task.mapper.SolutionMapper;
import ru.leti.wise.task.task.repository.SolutionRepository;

import static java.util.UUID.fromString;

@Component
@RequiredArgsConstructor
public class GetUserSolutionStatisticOperation {

    private final SolutionMapper solutionMapper;
    private final SolutionRepository solutionRepository;

    public GetUserSolutionStatisticResponse activate(GetUserSolutionStatisticRequest request) {
        var solutions = solutionRepository.findSolutionEntitiesByAuthorId(fromString(request.getAuthorId()));

        return TaskGrpc.GetUserSolutionStatisticResponse.newBuilder()
                .addAllSolution(solutionMapper.toSolutions(solutions))
                .build();
    }
}
