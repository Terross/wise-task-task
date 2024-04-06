package ru.leti.wise.task.task.model.task;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "task")
@Inheritance(strategy = InheritanceType.JOINED)
public class Task {

    @Id
    private UUID id;

    private String name;

    private String description;

    private String category;

    @Column(name = "task_type")
    @Enumerated(EnumType.STRING)
    private TaskType taskType;

    @Column(name = "author_id")
    private UUID authorId;

    @Column(name = "is_public")
    private Boolean isPublic;
}

