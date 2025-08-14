package com.vinehds.dailysinc.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vinehds.dailysinc.model.entitie.Daily;
import com.vinehds.dailysinc.model.entitie.Developer;
import com.vinehds.dailysinc.model.entitie.Team;
import com.vinehds.dailysinc.model.enums.ResponsabilityType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeveloperDTO {

    private Long id;

    private String name;

    private Team team;

    private List<Daily> dailies;

    private ResponsabilityType responsability;

    public Developer toEntity() {
        Developer developer = new Developer();

        developer.setId(id);
        developer.setName(name);
        developer.setTeam(team);
        developer.setResponsability(responsability);
        developer.setDailies(dailies);

        return developer;
    }

    public DeveloperDTO toDTO(Developer entity) {
        return fillDTO(entity);
    }

    public static DeveloperDTO fillDTO(Developer entity) {
        DeveloperDTO dto = new DeveloperDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setTeam(entity.getTeam());
        dto.setResponsability(entity.getResponsability());
        dto.setDailies(entity.getDailies());

        return dto;
    }

}
