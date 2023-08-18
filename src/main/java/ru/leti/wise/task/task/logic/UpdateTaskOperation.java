package ru.leti.wise.task.task.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.task.TaskGrpc;
import ru.leti.wise.task.task.TaskGrpc.UpdateTaskRequest;
import ru.leti.wise.task.task.TaskGrpc.UpdateTaskResponse;
import ru.leti.wise.task.task.error.BusinessException;
import ru.leti.wise.task.task.error.ErrorCode;
import ru.leti.wise.task.task.mapper.TaskMapper;
import ru.leti.wise.task.task.repository.TaskRepository;
import ru.leti.wise.task.task.service.GraphGrpcService;

@Component
@RequiredArgsConstructor
public class UpdateTaskOperation {

    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;
    private final GraphGrpcService graphGrpcService;

    public UpdateTaskResponse activate(UpdateTaskRequest request) {
        var taskEntity = taskMapper.toTask(request.getTask());
        taskRepository.findById(taskEntity.getId())
                .orElseThrow(() -> new BusinessException(ErrorCode.TASK_NOT_FOUND));
        graphGrpcService.createGraph(request.getTask().getGraph());
        taskRepository.save(taskEntity);
        return TaskGrpc.UpdateTaskResponse.newBuilder()
                .setTask(request.getTask())
                .build();
    }
}
