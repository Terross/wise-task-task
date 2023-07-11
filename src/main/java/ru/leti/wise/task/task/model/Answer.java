package ru.leti.wise.task.task.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Answer {
    @Id
    UUID id;

    @Data
    @Entity
    @EqualsAndHashCode(callSuper=true)
    public static class Property extends Answer {
        boolean response;
    }

    @Data
    @Entity
    @EqualsAndHashCode(callSuper=true)
    public static class Characteristic extends Answer {
        int response;
    }

    //TODO: А надо ли?
    @Data
    @Entity
    public static class Handwritten extends Answer {
        String response;
    }
}
