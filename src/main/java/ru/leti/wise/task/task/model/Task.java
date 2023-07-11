package ru.leti.wise.task.task.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Task {
    @Id
    private UUID id;
    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "author_id")
    private UUID authorId;

    @Column(name = "graph_id")
    private UUID graphId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "task_id")
    private List<Condition> conditions;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private List<Solution> solutions;
}
