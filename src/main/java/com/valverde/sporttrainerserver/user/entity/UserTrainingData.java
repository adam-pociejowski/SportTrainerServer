package com.valverde.sporttrainerserver.user.entity;

import com.valverde.sporttrainerserver.user.enums.Country;
import com.valverde.sporttrainerserver.user.enums.Gender;
import com.valverde.sporttrainerserver.user.enums.MeterUnits;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class UserTrainingData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    private String displayName;

    private Integer height;

    private Integer weight;

    @Enumerated(EnumType.STRING)
    private MeterUnits meterUnits;

    @Enumerated(EnumType.STRING)
    private Country country;

    @Enumerated(EnumType.STRING)
    private Gender gender;
}