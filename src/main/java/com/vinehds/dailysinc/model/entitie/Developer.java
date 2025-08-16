package com.vinehds.dailysinc.model.entitie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vinehds.dailysinc.model.enums.Department;
import com.vinehds.dailysinc.model.enums.Responsability;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_developers")
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dev_name")
    private String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dev_team", nullable = false)
    private Team team;

    @JsonIgnore
    @OneToMany(mappedBy = "author")
    private List<Daily> dailies;

    @Column(name = "dev_responsability")
    private Responsability responsability;

    @Column(name = "dev_departament")
    private Department department;
}
