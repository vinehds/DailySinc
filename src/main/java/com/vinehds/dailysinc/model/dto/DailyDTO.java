package com.vinehds.dailysinc.model.dto;

import com.vinehds.dailysinc.model.entitie.Daily;
import com.vinehds.dailysinc.model.entitie.Developer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyDTO {

    private Long id;

    private Developer author;

    private LocalDate date;

    private String lastDayLog;

    private String nextDayPlan;

    public Daily toEntity() {
        Daily entity = new Daily();

        entity.setId(id);
        entity.setAuthor(author);
        entity.setDate(date);
        entity.setLastDayLog(lastDayLog);
        entity.setNextDayPlan(nextDayPlan);
        return entity;
    }

    public DailyDTO toDTO(Daily entity) {
        return fillDTO(entity);
    }

    public static DailyDTO fillDTO(Daily entity) {
        DailyDTO dto = new DailyDTO();

        dto.setId(entity.getId());
        dto.setAuthor(entity.getAuthor());
        dto.setDate(entity.getDate());
        dto.setLastDayLog(entity.getLastDayLog());
        dto.setNextDayPlan(entity.getNextDayPlan());
        return dto;
    }

}
