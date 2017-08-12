package com.valverde.sporttrainerserver.activity.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class TrackPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Activity activity;

    private Double latitude;

    private Double longitude;

    private Double distance;

    private Double altitude;

    private Long time;
}
