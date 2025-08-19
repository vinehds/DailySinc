package com.vinehds.dailysinc.service;

import com.vinehds.dailysinc.model.dto.DailyDTO;
import com.vinehds.dailysinc.model.entitie.Daily;
import com.vinehds.dailysinc.repository.DailyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DailyService {

    private final DailyRepository dailyRepository;

    private final DeveloperService developerService;

    public List<DailyDTO> getAllDailies() {
        try{
            return dailyRepository.findAll().stream().map(DailyDTO::fromEntity).toList();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public DailyDTO getDailyById(Long id) {
        Optional<Daily> daily = dailyRepository.findById(id);
        return DailyDTO
                .fromEntity(daily.orElseThrow(() -> new RuntimeException("Daily not found")));
    }

    public DailyDTO insertDaily(DailyDTO daily) {
        Daily entity = daily.toEntity();
        entity.setAuthor(developerService.getDeveloperById(daily.getAuthorId()).toEntity());
        return DailyDTO.fromEntity(dailyRepository.save(entity));
    }

    public DailyDTO updateDaily(Long id, DailyDTO obj) {
        try {

            if(!isExists(id)){
                throw new RuntimeException("Daily not found");
            }

            Daily entity = dailyRepository.getReferenceById(id);
            updateData(entity, obj);

            return DailyDTO.fromEntity(dailyRepository.save(entity));
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void delete(Long id){
        try {
            dailyRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException();
        }
    }

    private boolean isExists(Long id) {
        return dailyRepository.existsById(id);
    }

    private void updateData(Daily entity, DailyDTO obj) {
        entity.setDate(obj.getDate());
        entity.setLastDayLog(obj.getLastDayLog());
        entity.setNextDayPlan(obj.getNextDayPlan());
    }
}
