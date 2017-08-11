package com.valverde.sporttrainerserver.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
class Split {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "activity_id")
    private Activity activity;

    private Integer kilometerNumber;

    private Long time;
}