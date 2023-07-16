package ru.leti.wise.task.task.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import ru.leti.wise.task.task.TaskOuterClass;
import ru.leti.wise.task.task.model.Answer;
import ru.leti.wise.task.task.model.Category;
import ru.leti.wise.task.task.model.Condition;
import ru.leti.wise.task.task.model.Task;

import java.util.List;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface TaskMapper {

    TaskOuterClass.Task toTask(Task task);

    TaskOuterClass.Condition toCondition(Condition condition);

    default TaskOuterClass.Answer toAnswer(Answer answer) {
        var builder = TaskOuterClass.Answer.newBuilder()
                .setId(answer.getId().toString());
        if (answer instanceof Answer.Property property) {
            builder.setProperty(TaskOuterClass.Property.newBuilder().
                    setResponse(property.isResponse()).build());
        } else if (answer instanceof Answer.Characteristic characteristic) {
            builder.setCharacteristic(TaskOuterClass.Characteristic.newBuilder()
                    .setResponse(characteristic.getResponse()).build());
        }
        return builder.build();
    }

    default TaskOuterClass.Category toCategory(Category category) {
        return TaskOuterClass.Category.valueOf(category.name());
    }

}
