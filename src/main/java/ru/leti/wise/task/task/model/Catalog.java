package ru.leti.wise.task.task.model;

import jakarta.persistence.*;
import lombok.Data;
import ru.leti.wise.task.task.model.task.Task;

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

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "task_catalog",
            joinColumns = { @JoinColumn(name = "catalog_id") },
            inverseJoinColumns = { @JoinColumn(name = "task_id") }
    )
    private List<Task> tasks;
}
