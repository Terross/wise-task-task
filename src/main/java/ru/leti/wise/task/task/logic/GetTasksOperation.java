package ru.leti.wise.task.task.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.task.TaskGrpc.GetAllTaskResponse;
import ru.leti.wise.task.task.mapper.TaskMapper;
import ru.leti.wise.task.task.repository.TaskRepository;

@Component
@RequiredArgsConstructor
public class GetTasksOperation {

    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;

    public GetAllTaskResponse activate() {
        var tasks = taskMapper.toTasks(taskRepository.findAll());
        return GetAllTaskResponse.newBuilder()
                .addAllTask(tasks)
                .build();
    }
}
