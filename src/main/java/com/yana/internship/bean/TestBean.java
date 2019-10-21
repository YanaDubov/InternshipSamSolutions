package com.yana.internship.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Table(name = "test")
public class TestBean {

    private int id;
    private String name;

    public TestBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public TestBean() {
    }

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
