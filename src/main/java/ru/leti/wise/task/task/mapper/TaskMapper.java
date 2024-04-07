package ru.leti.wise.task.task.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.leti.wise.task.plugin.PluginOuterClass;
import ru.leti.wise.task.task.TaskOuterClass;
import ru.leti.wise.task.task.error.BusinessException;
import ru.leti.wise.task.task.error.ErrorCode;
import ru.leti.wise.task.task.model.task.*;


import java.util.List;
import java.util.UUID;

import static java.util.UUID.fromString;
import static ru.leti.wise.task.task.error.ErrorCode.INVALID_TASK_TYPE;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface TaskMapper {

    default Task toTask(TaskOuterClass.Task task) {
        if (task.hasTaskGraph()) {
            return toTaskGraph(task);
        } else if (task.hasTaskImplementation()) {
            return toTaskImplementation(task);
        }
        throw new BusinessException(INVALID_TASK_TYPE);
    }

    @Mapping(target = "graphId", source = "task.taskGraph.graph.id")
    @Mapping(target = ".", source = "task.taskGraph")
    TaskGraph toTaskGraph(TaskOuterClass.Task task);

    @Mapping(target = ".", source = "task.taskImplementation")
    TaskImplementation toTaskImplementation(TaskOuterClass.Task task);

    default TaskOuterClass.Task toTask(Task task) {
        if (task instanceof TaskGraph) {
            return toTaskGraph((TaskGraph) task);
        } else if (task instanceof TaskImplementation) {
            return toTaskImplementation((TaskImplementation) task);
        }
        throw new BusinessException(INVALID_TASK_TYPE);
    }

    @Mapping(target = "taskImplementation", ignore = true)
    @Mapping(target = "taskGraph.graph.id", source = "graphId")
    @Mapping(target = "taskGraph.isHiddenMistake", source = "isHiddenMistake")
    @Mapping(target = "taskGraph.condition", source = "condition")
    @Mapping(target = "taskGraph.rule", source = "rule")
    TaskOuterClass.Task toTaskGraph(TaskGraph task);

    @Mapping(target = "taskGraph", ignore = true)
    @Mapping(target = "taskImplementation", source = ".")
    TaskOuterClass.Task toTaskImplementation(TaskImplementation task);

    default TaskOuterClass.TaskType toTaskType(TaskType taskType) {
        return TaskOuterClass.TaskType.valueOf(taskType.name());
    }

    default TaskOuterClass.PluginType toPluginType(PluginType pluginType) {
        return TaskOuterClass.PluginType.valueOf(pluginType.name());
    }

    List<TaskOuterClass.Task> toTasks(List<Task> tasks);

}
