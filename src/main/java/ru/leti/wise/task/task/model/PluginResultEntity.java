package ru.leti.wise.task.task.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "plugin_result")
public class PluginResultEntity {

    @Id
    private UUID id;

    @Column(name = "plugin_id")
    private UUID pluginId;

    @Column(name = "is_correct")
    private boolean isCorrect;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "excepted_answer_id")
    private Answer exceptedAnswer;
}
