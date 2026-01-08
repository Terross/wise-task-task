package ru.leti.wise.task.task.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.task.error.BusinessException;
import ru.leti.wise.task.task.error.ErrorCode;
import ru.leti.wise.task.task.repository.SolutionRepository;
import ru.leti.wise.task.task.repository.TaskRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DeleteTaskOperation {

    private final TaskRepository taskRepository;
    private final SolutionRepository solutionRepository;

    public void activate(UUID id) {
        if(taskRepository.findById(id).isEmpty()){
            throw new BusinessException(ErrorCode.TASK_NOT_FOUND);
        }
        solutionRepository.deleteByTaskId(id);
        // todo удаление задач из каталогов
        taskRepository.deleteById(id);
    }
}
