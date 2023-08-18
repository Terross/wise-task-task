package ru.leti.wise.task.task.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.task.repository.TaskRepository;
import ru.leti.wise.task.task.service.graph.GraphGrpcService;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DeleteTaskOperation {

    private final TaskRepository taskRepository;
    private final GraphGrpcService graphGrpcService;

    public void activate(UUID id) {
        taskRepository.deleteById(id);
    }
}
