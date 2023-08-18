package ru.leti.wise.task.task.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.leti.wise.task.task.TaskOuterClass;
import ru.leti.wise.task.task.model.SolutionEntity;

import java.util.UUID;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface SolutionMapper {

    @Mapping(target = "correct", ignore = true)
    @Mapping(target = "graphId", source = "solution", qualifiedByName = "mapGraphId")
    @Mapping(target = "pluginResultEntities", ignore = true)
    SolutionEntity toSolution(TaskOuterClass.SolutionRequest solution);

    @Named("mapGraphId")
    default UUID mapGraphId(TaskOuterClass.SolutionRequest solution) {
        return UUID.fromString(solution.getGraph().getId());
    }
}
