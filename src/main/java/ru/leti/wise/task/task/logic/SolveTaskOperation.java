package ru.leti.wise.task.task.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.task.TaskGrpc.SolveTaskRequest;
import ru.leti.wise.task.task.TaskGrpc.SolveTaskResponse;
import ru.leti.wise.task.task.error.BusinessException;
import ru.leti.wise.task.task.service.task.TaskGraphService;
import ru.leti.wise.task.task.service.task.TaskImplementationService;

import static ru.leti.wise.task.task.error.ErrorCode.INVALID_SOLUTION_TYPE;

@Component
@RequiredArgsConstructor
public class SolveTaskOperation {

    private final TaskGraphService taskGraphService;
    private final TaskImplementationService taskImplementationService;

    public SolveTaskResponse activate(SolveTaskRequest request) {
        if (request.getSolution().hasSolutionImplementation()) {
            return taskImplementationService.process(request);
        } else if (request.getSolution().hasSolutionGraph()) {
            return taskGraphService.process(request);
        }
        throw new BusinessException(INVALID_SOLUTION_TYPE);
    }
}
