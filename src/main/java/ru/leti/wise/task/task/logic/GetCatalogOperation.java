package ru.leti.wise.task.task.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.task.TaskGrpc;
import ru.leti.wise.task.task.error.BusinessException;
import ru.leti.wise.task.task.mapper.CatalogMapper;
import ru.leti.wise.task.task.repository.CatalogRepository;

import java.util.UUID;

import static ru.leti.wise.task.task.error.ErrorCode.CATALOG_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class GetCatalogOperation {

    private final CatalogMapper catalogMapper;
    private final CatalogRepository catalogRepository;

    public TaskGrpc.GetCatalogResponse activate(UUID id) {
        var catalog =catalogRepository.findById(id)
                .orElseThrow(() -> new BusinessException(CATALOG_NOT_FOUND));

        return TaskGrpc.GetCatalogResponse.newBuilder()
                .setCatalog(catalogMapper.toCatalog(catalog))
                .build();
    }
}
