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

    private TeamDTO team;

    private List<DailyDTO> dailies;

    private Department department;

    private Responsability responsability;

    public Developer toEntity() {
        Developer developer = new Developer();

        developer.setId(id);
        developer.setName(name);
        developer.setTeam(team.toEntity());
        developer.setResponsability(responsability);
        developer.setDailies(
                dailies.stream()
                .map(DailyDTO::toEntity)
                .collect(Collectors.toList())
        );
        developer.setDepartment(department);

        return developer;
    }

    public DeveloperDTO toDTO(Developer entity) {
        return fillDTO(entity);
    }

    public static DeveloperDTO fillDTO(Developer entity) {
        DeveloperDTO dto = new DeveloperDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setTeam(TeamDTO.fillDTO(entity.getTeam()));
        dto.setResponsability(entity.getResponsability());
        dto.setDailies(
                entity.getDailies().stream()
                        .map(DailyDTO::fillDTO)
                        .collect(Collectors.toList())
        );
        dto.setDepartment(entity.getDepartment());

        return dto;
    }

}
