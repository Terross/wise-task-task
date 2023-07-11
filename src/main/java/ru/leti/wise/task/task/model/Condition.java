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
    private UUID taskId;
    private UUID pluginId;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Answer answer;
}
