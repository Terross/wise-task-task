package ru.leti.wise.task.task.model.task;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@Entity
@Table(name = "task_implementation")
@EqualsAndHashCode(callSuper = true)
public class TaskImplementation extends Task {

    @Column(name = "plugin_id")
    private UUID pluginId;
}
