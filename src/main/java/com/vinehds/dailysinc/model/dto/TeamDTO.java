package com.vinehds.dailysinc.model.dto;

import com.vinehds.dailysinc.model.entitie.Developer;
import com.vinehds.dailysinc.model.entitie.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamDTO {

    private Long id;

    private List<Developer> members;

    private String description;

    public Team toEntity() {
        Team entity = new Team();

        entity.setId(id);
        entity.setMembers(members);
        entity.setDescription(description);

        return entity;
    }

    public TeamDTO toDTO(Team entity) {
        return fillDTO(entity);
    }

    public static TeamDTO fillDTO(Team entity) {
        TeamDTO dto = new TeamDTO();

        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setMembers(entity.getMembers());

        return dto;
    }
}
