package com.vinehds.dailysinc.service;

import com.vinehds.dailysinc.model.dto.DeveloperDTO;
import com.vinehds.dailysinc.model.entitie.Developer;
import com.vinehds.dailysinc.repository.DeveloperRepository;
import com.vinehds.dailysinc.service.exception.DataBaseException;
import com.vinehds.dailysinc.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeveloperService {

    private final DeveloperRepository developerRepository;

    private final TeamService teamService;

    public List<DeveloperDTO> getAllDevelopers() {
        try{
            return developerRepository.findAll().stream().map(DeveloperDTO::fromEntity).toList();
        } catch (Exception e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public DeveloperDTO getDeveloperById(Long id) {
        Optional<Developer> dev = developerRepository.findById(id);
        return DeveloperDTO
                .fromEntity(dev.orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    public DeveloperDTO insertDeveloper(DeveloperDTO developer) {
        Developer entity = developer.toEntity();

        if(developer.getTeamId() != null){
            entity.setTeam(teamService.getTeamById(developer.getTeamId()).toEntity());
        }
        return DeveloperDTO.fromEntity(developerRepository.save(entity));
    }

    public DeveloperDTO updateDeveloper(Long id, DeveloperDTO obj) {
        try {
            if(!isExists(id)){
                throw new ResourceNotFoundException(id);
            }

            Developer entity = developerRepository.getReferenceById(id);
            updateData(entity, obj);

            return DeveloperDTO.fromEntity(developerRepository.save(entity));
        }catch (Exception e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public void delete(Long id){
        try {
            developerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private boolean isExists(Long id) {
        return developerRepository.existsById(id);
    }

    private void updateData(Developer entity, DeveloperDTO obj) {
        entity.setName(obj.getName());
        entity.setUserRole(obj.getUserRole());
        entity.setDepartment(obj.getDepartment());
    }

}
