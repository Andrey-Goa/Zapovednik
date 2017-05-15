package ru.zapovednik.db;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by andrey-goa on 11.05.17.
 */
@Entity
public class Tag {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private State state;

    public String getName() {
        return name;
    }

    public State getState() {
        return state;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setState(State state) {
        this.state = state;
    }

}
