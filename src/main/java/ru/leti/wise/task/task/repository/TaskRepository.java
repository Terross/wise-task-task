package ru.leti.wise.task.task.repository;

import io.micrometer.observation.annotation.Observed;
import org.springframework.data.repository.CrudRepository;
import ru.leti.wise.task.task.model.TaskEntity;

import java.util.List;
import java.util.UUID;

@Observed
public interface TaskRepository extends CrudRepository<TaskEntity, UUID> {

    List<TaskEntity> findAll();
}
