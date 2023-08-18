package ru.leti.wise.task.task.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Answer {
    @Id
    protected UUID id;

    @Data
    @Entity
    @SuperBuilder
    @NoArgsConstructor
    @Table(name = "property")
    @EqualsAndHashCode(callSuper=true)
    public static class Property extends Answer {
        @Column(name = "property_value")
        boolean value;
    }

    @Data
    @Entity
    @SuperBuilder
    @NoArgsConstructor
    @Table(name = "characteristic")
    @EqualsAndHashCode(callSuper=true)
    public static class Characteristic extends Answer {
        @Column(name = "characteristic_value")
        int value;
    }

}
