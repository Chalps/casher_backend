package com.elit.casher_backend.model;

import com.elit.casher_backend.model.enums.UserType;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity(name = "User")
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"ID"})})
public class User implements Serializable {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FULLNAME", nullable = false)
    private String fullname;

    @Column(name = "NICKNAME", nullable = false)
    private String nickname;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "PHOTOURL")
    private String photoUrl;

    @Column(name = "BIRTHDATE", nullable = false)
    private Date birthdate;

    @Column(name = "TYPE", nullable = false)
    private UserType type;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "PROVIDER")
    private String provider;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "MONEY")
    private Integer money;

    @Column(name = "CASHER_POINTS")
    private Integer casherPoints;

    @Column(name = "ID_LEVEL")
    private Integer levelId;

    @Column(name = "ID_LEAGUE")
    private Integer leagueId;

    @Column(name = "STREAK")
    private Integer streak;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getCasherPoints() {
        return casherPoints;
    }

    public void setCasherPoints(Integer casherPoints) {
        this.casherPoints = casherPoints;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Integer getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
    }

    public Integer getStreak() {
        return streak;
    }

    public void setStreak(Integer streak) {
        this.streak = streak;
    }
}
