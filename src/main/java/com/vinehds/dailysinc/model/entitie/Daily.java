package com.vinehds.dailysinc.model.entitie;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_dailies")
public class Daily {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Developer author;

    private LocalDate date;

    @Column(name = "last_day_log")
    private String lastDayLog;

    @Column(name = "next_day_plan")
    private String nextDayPlan;

}
