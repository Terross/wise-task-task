package ru.leti.wise.task.task.repository;

import org.springframework.data.repository.CrudRepository;
import ru.leti.wise.task.task.model.TaskEntity;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends CrudRepository<TaskEntity, UUID> {

    List<TaskEntity> findAll();
}
