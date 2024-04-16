package ru.leti.wise.task.task.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "catalog")
public class Catalog {

    @Id
    private UUID id;

    private String name;

    private String catalog;

    @Column(name = "author_id")
    private UUID authorId;

    @ElementCollection
    private List<UUID> studentIds;

    @ElementCollection
    @CollectionTable(name = "task_catalog", joinColumns = @JoinColumn(name = "catalog_id"))
    @Column(name = "task_id")
    private List<UUID> taskIds;
}
