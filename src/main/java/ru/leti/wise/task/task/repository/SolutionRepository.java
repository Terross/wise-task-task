package ru.leti.wise.task.task.repository;

import org.springframework.data.repository.CrudRepository;
import ru.leti.wise.task.task.model.SolutionEntity;

import java.util.UUID;

public interface SolutionRepository extends CrudRepository<SolutionEntity, UUID> {
}