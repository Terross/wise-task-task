package ru.leti.wise.task.task.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Solution {

    @Id
    private UUID id;

    @Column(name = "task_id")
    private UUID taskId;

    @Column(name = "author_id")
    private UUID authorId;

    private boolean isCorrect;

    @Column(name = "graph_id")
    private UUID graphId;
}
