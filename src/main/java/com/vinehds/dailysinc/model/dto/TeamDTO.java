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

    private List<Long> membersId;

    private String description;

    public Team toEntity() {
        Team entity = new Team();

        entity.setId(id);
        entity.setDescription(description);

        return entity;
    }

    public static TeamDTO fromEntity(Team entity) {
        TeamDTO dto = new TeamDTO();

        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setMembersId(entity.getMembers().stream().map(dev -> dev.getId())
                .collect(Collectors.toList()));

        return dto;
    }
}
