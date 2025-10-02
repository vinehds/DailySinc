package com.vinehds.dailysinc.model.dto;

import com.vinehds.dailysinc.model.entitie.Daily;
import com.vinehds.dailysinc.model.entitie.Developer;
import com.vinehds.dailysinc.model.enums.Department;
import com.vinehds.dailysinc.model.enums.UserRole;
import com.vinehds.dailysinc.validation.annotation.BusinessEmail;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeveloperDTO {

    private Long id;

    private String name;

    private Long teamId;

    @BusinessEmail
    private String email;

    private List<Long> dailiesId = new ArrayList<>();

    private Department department;

    private UserRole UserRole;

    private String password;

    public Developer toEntity() {
        Developer developer = new Developer();

        developer.setId(id);
        developer.setName(name);
        developer.setUserRole(UserRole);
        developer.setDepartment(department);
        developer.setEmail(email);
        developer.setPassword(password);

        return developer;
    }

    public static DeveloperDTO fromEntity(Developer entity) {
        DeveloperDTO dto = new DeveloperDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());

        if(entity.getTeam() != null) {
            dto.setTeamId(entity.getTeam().getId());
        }

        dto.setUserRole(entity.getUserRole());
        dto.setDepartment(entity.getDepartment());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());

        if(entity.getDailies() != null){
            dto.setDailiesId(entity.getDailies().stream()
                    .map(Daily::getId).toList());
        }

        return dto;
    }

}
