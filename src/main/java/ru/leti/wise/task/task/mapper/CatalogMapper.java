package ru.leti.wise.task.task.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import ru.leti.wise.task.task.TaskOuterClass;
import ru.leti.wise.task.task.model.Catalog;

import java.util.List;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface CatalogMapper {

    TaskOuterClass.Catalog toCatalog(Catalog catalog);

    List<TaskOuterClass.Catalog> toCatalogs(List<Catalog> catalogs);

    Catalog toCatalog(TaskOuterClass.Catalog catalog);
}
