package com.valverde.sporttrainerserver.zwift.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class RiderState {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private ZwiftActivity activity;

    private Integer time;

    private Double speed;

    private Integer roadPosition;

    private Integer distance;

    private Integer climbing;

    private Double altitude;

    private Integer power;
}
