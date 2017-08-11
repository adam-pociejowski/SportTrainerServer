package com.valverde.sporttrainerserver.entity;

import lombok.Data;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "user")
    private List<Activity> activities;

    private String username;

    private String passwordHash;

    @Temporal(TemporalType.DATE)
    private Date lastPasswordResetDate;
}
