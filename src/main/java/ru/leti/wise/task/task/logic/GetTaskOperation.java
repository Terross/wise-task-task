package ru.leti.wise.task.task.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.task.TaskGrpc;
import ru.leti.wise.task.task.error.BusinessException;
import ru.leti.wise.task.task.error.ErrorCode;
import ru.leti.wise.task.task.mapper.TaskMapper;
import ru.leti.wise.task.task.repository.TaskRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetTaskOperation {

    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;

    public TaskGrpc.GetTaskResponse activate(UUID fromString) {
        var task = taskRepository.findById(fromString)
                .orElseThrow(() -> new BusinessException(ErrorCode.TASK_NOT_FOUND));
        return TaskGrpc.GetTaskResponse.newBuilder()
                .setTask(taskMapper.toTask(task))
                .build();
    }
}
