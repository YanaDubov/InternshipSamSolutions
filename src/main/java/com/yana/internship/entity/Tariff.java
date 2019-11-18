package com.yana.internship.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tariff")
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "cost_per_night")
    private BigDecimal costPerNight;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCostPerNight() {
        return costPerNight;
    }

    public void setCostPerNight(BigDecimal costPerNight) {
        this.costPerNight = costPerNight;
    }

}
