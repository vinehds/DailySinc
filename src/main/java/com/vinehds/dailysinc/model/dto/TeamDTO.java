package com.vinehds.dailysinc.model.dto;

import com.vinehds.dailysinc.model.entitie.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamDTO {

    private Long id;

    private List<DeveloperDTO> members;

    private String description;

    public Team toEntity() {
        Team entity = new Team();

        entity.setId(id);
        entity.setMembers(members.stream().map(DeveloperDTO::toEntity)
                .collect(Collectors.toList()));
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
        dto.setMembers(entity.getMembers().stream().map(DeveloperDTO::fillDTO)
                .collect(Collectors.toList()));

        return dto;
    }
}
