package ru.leti.wise.task.task.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.task.TaskGrpc;
import ru.leti.wise.task.task.mapper.CatalogMapper;
import ru.leti.wise.task.task.repository.CatalogRepository;

@Component
@RequiredArgsConstructor
public class CreateCatalogOperation {

    private final CatalogMapper catalogMapper;
    private final CatalogRepository catalogRepository;

    public TaskGrpc.CreateCatalogResponse activate(TaskGrpc.CreateCatalogRequest request) {

        var catalog = catalogMapper.toCatalog(request.getCatalog());
        catalogRepository.save(catalog);
        return TaskGrpc.CreateCatalogResponse.newBuilder()
                .setCatalog(request.getCatalog())
                .build();
    }
}
