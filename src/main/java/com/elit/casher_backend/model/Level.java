package com.elit.casher_backend.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity(name = "Level")
@Table(name = "level", uniqueConstraints = {@UniqueConstraint(columnNames = {"ID"})}, schema = "casher")
public class Level implements Serializable {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "POSITION")
    private Integer position;

    @JoinColumn(name = "ID_TRAIL")
    @OneToMany(fetch = FetchType.LAZY)
    private List<Trail> trail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public List<Trail> getTrail() {
        return trail;
    }

    public void setTrail(List<Trail> trail) {
        this.trail = trail;
    }
}
