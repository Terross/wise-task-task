package ru.leti.wise.task.task.logic;

import com.google.protobuf.Empty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.task.repository.CatalogRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DeleteCatalogOperation {

    private final CatalogRepository catalogRepository;

    public Empty activate(UUID uuid) {

        catalogRepository.deleteById(uuid);

        return Empty.newBuilder().build();
    }
}
