package ru.leti.wise.task.task.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.leti.wise.task.graph.GraphOuterClass;
import ru.leti.wise.task.plugin.PluginOuterClass;
import ru.leti.wise.task.task.TaskOuterClass;
import ru.leti.wise.task.task.model.PluginResultEntity;
import ru.leti.wise.task.task.model.PluginType;
import ru.leti.wise.task.task.model.SolutionEntity;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface SolutionMapper {

    @Mapping(target = "correct", ignore = true)
    @Mapping(target = "graphId", source = "solution", qualifiedByName = "mapGraphId")
    @Mapping(target = "pluginResultEntities", ignore = true)
    SolutionEntity toSolution(TaskOuterClass.SolutionRequest solution);

    @Mapping(target = "pluginResultList", source = "pluginResultEntities")
    @Mapping(target = "isCorrect", source = "correct")
    @Mapping(target = "graph", ignore = true)
    TaskOuterClass.SolutionResponse toSolution(SolutionEntity solution);

    @Mapping(target = "pluginResultList", source = "pluginResultEntities")
    @Mapping(target = "isCorrect", source = "correct")
    @Mapping(target = "graph", ignore = true)
    List<TaskOuterClass.SolutionResponse> toSolutions(List<SolutionEntity> solutions);

    @Mapping(target = "id", source = "solution.id")
    @Mapping(target = "pluginResultList", source = "solution.pluginResultEntities")
    @Mapping(target = "isCorrect", source = "solution.correct")
    @Mapping(target = "graph", source = "graph")
    TaskOuterClass.SolutionResponse toSolutionWithGraph(SolutionEntity solution, GraphOuterClass.Graph graph);

    @Mapping(target = "isCorrect", source = "correct")
    TaskOuterClass.PluginResult toPluginResult(PluginResultEntity pluginResult);

    @Named("mapGraphId")
    default UUID mapGraphId(TaskOuterClass.SolutionRequest solution) {
        return UUID.fromString(solution.getGraph().getId());
    }

    default PluginOuterClass.PluginType toPluginType(PluginType pluginType) {
        return PluginOuterClass.PluginType.valueOf(pluginType.name());
    }
}
