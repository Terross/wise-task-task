package ru.leti.wise.task.task.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.leti.wise.task.task.TaskOuterClass;
import ru.leti.wise.task.task.TaskOuterClass.Task;
import ru.leti.wise.task.task.model.Answer;
import ru.leti.wise.task.task.model.Category;
import ru.leti.wise.task.task.model.Condition;
import ru.leti.wise.task.task.model.TaskEntity;

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

    default TaskOuterClass.Answer toAnswer(Answer answer) {
        var builder = TaskOuterClass.Answer.newBuilder()
                .setId(answer.getId().toString());
        if (answer instanceof Answer.Property property) {
            builder.setProperty(TaskOuterClass.Property.newBuilder().
                    setValue(property.isValue()).build());
        } else if (answer instanceof Answer.Characteristic characteristic) {
            builder.setCharacteristic(TaskOuterClass.Characteristic.newBuilder()
                    .setValue(characteristic.getValue()).build());
        }
        return builder.build();
    }

    default Answer toAnswer(TaskOuterClass.Answer answer) {
        if (answer.hasCharacteristic()) {
            return Answer.Characteristic.builder()
                    .id(fromString(answer.getId()))
                    .value(answer.getCharacteristic().getValue())
                    .build();
        } else if (answer.hasProperty()) {
            return Answer.Property.builder()
                    .id(fromString(answer.getId()))
                    .value(answer.getProperty().getValue())
                    .build();
        }
        throw new RuntimeException(); //TODO:???
    }

    default TaskOuterClass.Category toCategory(Category category) {
        return TaskOuterClass.Category.valueOf(category.name());
    }

}
