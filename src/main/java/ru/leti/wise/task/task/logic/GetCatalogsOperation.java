package ru.leti.wise.task.task.logic;

import com.google.protobuf.Empty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.task.TaskGrpc;
import ru.leti.wise.task.task.mapper.CatalogMapper;
import ru.leti.wise.task.task.repository.CatalogRepository;

@Component
@RequiredArgsConstructor
public class GetCatalogsOperation {

    private final CatalogRepository catalogRepository;
    private final CatalogMapper catalogMapper;

    public TaskGrpc.GetAllCatalogsResponse activate(Empty request) {

        var catalogs = catalogMapper.toCatalogs(catalogRepository.findAll());

        return TaskGrpc.GetAllCatalogsResponse.newBuilder()
                .addAllCatalog(catalogs)
                .build();
    }
}
