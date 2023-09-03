package ru.leti.wise.task.task.repository;

import io.micrometer.observation.annotation.Observed;
import org.springframework.data.repository.CrudRepository;
import ru.leti.wise.task.task.model.SolutionEntity;

import java.util.List;
import java.util.UUID;

@Observed
public interface SolutionRepository extends CrudRepository<SolutionEntity, UUID> {

    List<SolutionEntity> findSolutionEntitiesByAuthorIdAndTaskId(UUID authorId, UUID taskId);

    List<SolutionEntity> findSolutionEntitiesByAuthorId(UUID authorId);
}
