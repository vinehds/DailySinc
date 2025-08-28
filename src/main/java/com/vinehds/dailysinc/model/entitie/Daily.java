package com.vinehds.dailysinc.model.entitie;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Developer author;

    private String authorName;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @Column(name = "last_day_log")
    private String lastDayLog;

    @Column(name = "next_day_plan")
    private String nextDayPlan;

}
