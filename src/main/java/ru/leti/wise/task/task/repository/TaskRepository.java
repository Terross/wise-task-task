package ru.leti.wise.task.task.repository;

import io.micrometer.observation.annotation.Observed;
import org.springframework.data.repository.CrudRepository;
import ru.leti.wise.task.task.model.task.Task;

import java.util.List;
import java.util.UUID;

@Observed
public interface TaskRepository extends CrudRepository<Task, UUID> {

    List<Task> findAll();
}
