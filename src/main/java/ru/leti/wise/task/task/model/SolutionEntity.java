package ru.leti.wise.task.task.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "solution")
public class SolutionEntity {

    @Id
    private UUID id;

    @Column(name = "task_id")
    private UUID taskId;

    @Column(name = "author_id")
    private UUID authorId;

    private boolean isCorrect;

    @Column(name = "graph_id")
    private UUID graphId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "solution_id")
    private List<PluginResultEntity> pluginResultEntities;
}
