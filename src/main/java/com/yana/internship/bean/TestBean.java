package com.yana.internship.bean;

import javax.persistence.*;

@Entity
@Table(name = "test")
public class TestBean {
    public TestBean(int id, String name) {
    this.id = id;
    this.name = name;
    }
    public TestBean() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;



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
}
