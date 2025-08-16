package com.vinehds.dailysinc.service;

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

    public List<Daily> getAllDailies() {
        try{
            return dailyRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Daily getDailyById(Long id) {
        Optional<Daily> daily = dailyRepository.findById(id);
        return daily.orElseThrow(() -> new RuntimeException("Daily not found"));
    }

    public Daily insertDaily(Daily daily) {
        if(isExists(daily.getId())) {
            throw new RuntimeException("Daily already exists: " + daily.getId());
        } else {
            return dailyRepository.save(daily);
        }
    }

    public Daily updateDaily(Long id, Daily obj) {
        try {
            if(!isExists(id)){
                throw new RuntimeException("Daily not found");
            }

            Daily entity = dailyRepository.getReferenceById(id);
            updateData(entity, obj);

            return dailyRepository.save(entity);
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

    private void updateData(Daily entity, Daily obj) {
        entity.setAuthor(obj.getAuthor());
        entity.setDate(obj.getDate());
        entity.setLastDayLog(obj.getLastDayLog());
        entity.setNextDayPlan(obj.getNextDayPlan());
    }
}
