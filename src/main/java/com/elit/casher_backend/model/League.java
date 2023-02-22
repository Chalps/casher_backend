package com.elit.casher_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "league", uniqueConstraints = {@UniqueConstraint(columnNames = {"ID"})}, schema = "casher")
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DOWN_SCORE")
    private String downScore;

    @Column(name = "UP_SCORE")
    private String upScore;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDownScore() {
        return downScore;
    }

    public void setDownScore(String downScore) {
        this.downScore = downScore;
    }

    public String getUpScore() {
        return upScore;
    }

    public void setUpScore(String upScore) {
        this.upScore = upScore;
    }

}
