package com.valverde.sporttrainerserver.activity.entity;

import com.valverde.sporttrainerserver.activity.enums.ActivityOrigin;
import com.valverde.sporttrainerserver.user.entity.User;
import com.valverde.sporttrainerserver.activity.enums.ActivityType;
import com.valverde.sporttrainerserver.statistics.entity.ActivityRecord;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
    private List<TrackPoint> trackPoints;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
    private List<ActivityRecord> activityRecords;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
    private List<Split> splits;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private ActivityType type;

    @Enumerated(EnumType.STRING)
    private ActivityOrigin origin;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private Long totalTime;

    private Double distance;

    private Integer calories;
}