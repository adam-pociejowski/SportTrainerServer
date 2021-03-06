package com.valverde.sporttrainerserver.statistics.entity;

import com.valverde.sporttrainerserver.activity.entity.Activity;
import com.valverde.sporttrainerserver.statistics.enums.RecordType;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class ActivityRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "activity_id")
    private Activity activity;

    @Enumerated(EnumType.STRING)
    private RecordType type;

    private Double value;
}
