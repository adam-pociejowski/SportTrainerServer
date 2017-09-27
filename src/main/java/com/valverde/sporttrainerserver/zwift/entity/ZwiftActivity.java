package com.valverde.sporttrainerserver.zwift.entity;

import com.valverde.sporttrainerserver.base.enums.State;
import com.valverde.sporttrainerserver.zwift.enums.ZwiftTrack;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class ZwiftActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
    private List<RiderState> states;

    @Enumerated(EnumType.STRING)
    private ZwiftTrack track;

    @Enumerated(EnumType.STRING)
    private State activityState;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    private String riderId;
}
