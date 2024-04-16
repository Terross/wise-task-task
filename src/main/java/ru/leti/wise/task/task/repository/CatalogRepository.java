package ru.leti.wise.task.task.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.leti.wise.task.task.model.Catalog;

import java.util.List;
import java.util.UUID;

@Repository
public interface CatalogRepository extends CrudRepository<Catalog, UUID> {

    List<Catalog> findAll();
}
