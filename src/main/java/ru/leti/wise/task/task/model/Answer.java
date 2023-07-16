package ru.leti.wise.task.task.model;

import jakarta.persistence.*;
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
    @Table(name = "property")
    @EqualsAndHashCode(callSuper=true)
    public static class Property extends Answer {
        @Column(name = "property_response")
        boolean response;
    }

    @Data
    @Entity
    @Table(name = "characteristic")
    @EqualsAndHashCode(callSuper=true)
    public static class Characteristic extends Answer {
        @Column(name = "characteristic_response")
        int response;
    }

//    //TODO: А надо ли?
//    @Data
//    @Entity
//    public static class Handwritten extends Answer {
//        String response;
//    }
}
