package com.yana.internship.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Entity
@Table(name = "test")
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_test")
    private int id;
    @Column(nullable = false)
    @NotEmpty(message = "Enter the name, please")
    @Size(min = 3)
    private String name;
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "testEntity")
    @JsonProperty(access = WRITE_ONLY)
    private List<LocalEntity> locals = new ArrayList<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LocalEntity> getLocals() {
        return locals;
    }

    public void setLocals(List<LocalEntity> locals) {
        this.locals = locals;
    }
}
