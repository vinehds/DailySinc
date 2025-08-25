package com.vinehds.dailysinc.model.dto;

import com.vinehds.dailysinc.model.entitie.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamDTO {

    private Long id;

    private String teamName;

    private List<Long> membersId = new ArrayList<>();

    private String description;

    public Team toEntity() {
        Team entity = new Team();

        entity.setId(id);
        entity.setDescription(description);
        entity.setTeamName(teamName);

        return entity;
    }

    public static TeamDTO fromEntity(Team entity) {
        TeamDTO dto = new TeamDTO();

        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setTeamName(entity.getTeamName());

        if(entity.getMembers() != null) {
            dto.setMembersId(entity.getMembers().stream().map(dev -> dev.getId()).toList());
        }

        return dto;
    }
}
