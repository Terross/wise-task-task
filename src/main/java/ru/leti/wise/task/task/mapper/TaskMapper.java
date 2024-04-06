package ru.leti.wise.task.task.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.leti.wise.task.plugin.PluginOuterClass;
import ru.leti.wise.task.task.TaskOuterClass;
import ru.leti.wise.task.task.TaskOuterClass.Task;

import java.util.List;
import java.util.UUID;

import static java.util.UUID.fromString;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface TaskMapper {

    @Mapping(target = "graph", ignore = true)
    Task toTask(TaskEntity task);

    @Mapping(target = "graphId", source = "task", qualifiedByName = "toGraphId")
    TaskEntity toTask(Task task);

    List<Task> toTasks(List<TaskEntity> tasks);

    TaskOuterClass.Condition toCondition(Condition condition);

    @Named("toGraphId")
    default UUID toGraphId(Task task) {
        return fromString(task.getGraph().getId());
    }

    default TaskOuterClass.Category toCategory(Category category) {
        return TaskOuterClass.Category.valueOf(category.name());
    }

    default PluginOuterClass.PluginType toPluginType(PluginType pluginType) {
        return PluginOuterClass.PluginType.valueOf(pluginType.name());
    }

}
