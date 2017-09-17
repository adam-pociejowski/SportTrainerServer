package com.valverde.sporttrainerserver.user.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import com.valverde.sporttrainerserver.activity.entity.Activity;

@Data
@Entity
@Table(name = "trainer_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private UserTrainingData userTrainingData;

    @OneToMany(mappedBy = "user")
    private List<Activity> activities;

    private String username;

    private String passwordHash;

    @Temporal(TemporalType.DATE)
    private Date registerDate;

    @Temporal(TemporalType.DATE)
    private Date lastPasswordResetDate;
}
