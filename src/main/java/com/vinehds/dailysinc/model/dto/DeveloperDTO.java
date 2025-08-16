package com.vinehds.dailysinc.model.dto;

import com.vinehds.dailysinc.model.entitie.Developer;
import com.vinehds.dailysinc.model.enums.Department;
import com.vinehds.dailysinc.model.enums.Responsability;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeveloperDTO {

    private Long id;

    private String name;

    private Long teamId;

    private List<Long> dailiesId;

    private Department department;

    private Responsability responsability;

    public Developer toEntity() {
        Developer developer = new Developer();

        developer.setId(id);
        developer.setName(name);
        developer.setResponsability(responsability);
        developer.setDepartment(department);

        return developer;
    }

    public static DeveloperDTO fromEntity(Developer entity) {
        DeveloperDTO dto = new DeveloperDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setTeamId(entity.getTeam().getId());
        dto.setResponsability(entity.getResponsability());
        dto.setDailiesId(
                entity.getDailies().stream()
                        .map(daily -> daily.getId())
                        .collect(Collectors.toList())
        );
        dto.setDepartment(entity.getDepartment());

        return dto;
    }

}
