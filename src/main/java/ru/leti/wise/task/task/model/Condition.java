package ru.leti.wise.task.task.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Condition {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "task_id")
    private UUID taskId;

    @Column(name = "plugin_id")
    private UUID pluginId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    @MapsId
    private Answer answer;
}
