package ru.zapovednik.db;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
    public class Tag {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @NotNull
        @Column(name = "name")
        private String name;
        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
