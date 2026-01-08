package ru.leti.wise.task.task.logic;

import com.google.protobuf.Empty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.task.error.BusinessException;
import ru.leti.wise.task.task.error.ErrorCode;
import ru.leti.wise.task.task.repository.CatalogRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DeleteCatalogOperation {

    private final CatalogRepository catalogRepository;

    public Empty activate(UUID uuid) {
        if(catalogRepository.findById(uuid).isEmpty()){
            throw new BusinessException(ErrorCode.CATALOG_NOT_FOUND);
        }
        catalogRepository.deleteById(uuid);
        //todo возможно нужно удаление task_catalog, также проверка
        return Empty.newBuilder().build();
    }
}
