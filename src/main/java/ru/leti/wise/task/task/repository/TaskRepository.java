package ru.leti.wise.task.task.repository;

import org.springframework.data.repository.CrudRepository;
import ru.leti.wise.task.task.model.Task;

import java.util.UUID;

public interface TaskRepository extends CrudRepository<Task, UUID> {
}
