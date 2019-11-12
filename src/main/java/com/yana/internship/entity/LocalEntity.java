package com.yana.internship.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "localtest")
public class LocalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_local")
    private int idLocal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Lang lang;

    @Column(name = "name_local")
    private String localName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_test")
    @JsonIgnore
    private TestEntity testEntity;

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public Lang getLang() {
        return lang;
    }

    public void setLang(Lang lang) {
        this.lang = lang;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public TestEntity getTestEntity() {
        return testEntity;
    }

    public void setTestEntity(TestEntity testEntity) {
        this.testEntity = testEntity;
    }
}
