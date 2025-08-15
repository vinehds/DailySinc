package com.vinehds.dailysinc.service;

import com.vinehds.dailysinc.model.entitie.Developer;
import com.vinehds.dailysinc.repository.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeveloperService {

    private final DeveloperRepository developerRepository;

    public List<Developer> getAllDevelopers() {
        try{
            return developerRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Developer getDeveloperById(Long id) {
        Optional<Developer> dev = developerRepository.findById(id);
        return dev.orElseThrow(() -> new RuntimeException("Developer not found"));
    }

    public Developer insertDeveloper(Developer developer) {
        if(isExists(developer.getId())) {
            throw new RuntimeException("Developer already exists: " + developer.getId());
        } else {
            return developerRepository.save(developer);
        }
    }

    public Developer updateDeveloper(Long id, Developer obj) {
        try {
            if(!isExists(id)){
                throw new RuntimeException("Developer not found");
            }

            Developer entity = developerRepository.getReferenceById(id);
            updateData(entity, obj);

            return developerRepository.save(entity);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void delete(Long id){
        try {
            developerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id.toString());
        }
    }

    private boolean isExists(Long id) {
        return developerRepository.existsById(id);
    }

    private void updateData(Developer entity, Developer obj) {
        entity.setName(obj.getName());
        entity.setTeam(obj.getTeam());
        entity.setResponsability(obj.getResponsability());
        entity.setDepartment(obj.getDepartment());
    }
}
