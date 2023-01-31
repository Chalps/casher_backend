package com.elit.casher_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "module", uniqueConstraints = {@UniqueConstraint(columnNames = {"ID"})})
public class Module {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "REWARD_MONEY")
    private Long rewardMoney;

    @Column(name = "REWARD_POINTS")
    private Long rewardPoints;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TRAIL")
    private Trail trail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRewardMoney() {
        return rewardMoney;
    }

    public void setRewardMoney(Long rewardMoney) {
        this.rewardMoney = rewardMoney;
    }

    public Long getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(Long rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public Trail getTrail() {
        return trail;
    }

    public void setTrail(Trail trail) {
        this.trail = trail;
    }
}
