package ru.leti.wise.task.task.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.task.TaskGrpc;
import ru.leti.wise.task.task.error.BusinessException;
import ru.leti.wise.task.task.error.ErrorCode;
import ru.leti.wise.task.task.mapper.CatalogMapper;
import ru.leti.wise.task.task.repository.CatalogRepository;

@Component
@RequiredArgsConstructor
public class UpdateCatalogOperation {

    private final CatalogMapper catalogMapper;
    private final CatalogRepository catalogRepository;

    public TaskGrpc.UpdateCatalogResponse activate(TaskGrpc.UpdateCatalogRequest request) {
        var catalog = catalogMapper.toCatalog(request.getCatalog());
        catalogRepository.findById(catalog.getId())
                .orElseThrow(() -> new BusinessException(ErrorCode.CATALOG_NOT_FOUND));
        catalogRepository.save(catalog);
        return TaskGrpc.UpdateCatalogResponse.newBuilder()
                .setCatalog(request.getCatalog())
                .build();
    }
}
