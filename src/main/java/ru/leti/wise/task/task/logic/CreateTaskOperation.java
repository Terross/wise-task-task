package ru.leti.wise.task.task.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.task.TaskGrpc.CreateTaskRequest;
import ru.leti.wise.task.task.TaskGrpc.CreateTaskResponse;
import ru.leti.wise.task.task.mapper.TaskMapper;
import ru.leti.wise.task.task.repository.TaskRepository;
import ru.leti.wise.task.task.service.grpc.graph.GraphGrpcService;

@Component
@RequiredArgsConstructor
public class CreateTaskOperation {

    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;
    private final GraphGrpcService graphGrpcService;

    public CreateTaskResponse activate(CreateTaskRequest request) {
        if (request.getTask().hasTaskGraph()) {
            graphGrpcService.createGraph(request.getTask().getTaskGraph().getGraph());
        }
        taskRepository.save(taskMapper.toTask(request.getTask()));
        return CreateTaskResponse.newBuilder()
                .setTask(request.getTask())
                .build();
    }
}
