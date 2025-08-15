package com.vinehds.dailysinc.service;

import com.vinehds.dailysinc.model.entitie.Team;
import com.vinehds.dailysinc.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamService { //todo exceptions customized

    private final TeamRepository teamRepository;

    public List<Team> getAllTeams() {
        try{
            return teamRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Team getTeamById(Long id) {
       Optional<Team> team = teamRepository.findById(id);
       return team.orElseThrow(() -> new RuntimeException("Team not found"));
    }

    public Team insertTeam(Team team) {
        if(isExists(team.getId())) {
            throw new RuntimeException("Team already exists: " + team.getId());
        } else {
            return teamRepository.save(team);
        }
    }

    public Team updateTeam(Long id, Team obj) {
        try {
            if(!isExists(id)){
                throw new RuntimeException("Team not found");
            }

            Team entity = teamRepository.getReferenceById(id);
            updateData(entity, obj);

            return teamRepository.save(entity);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void delete(Long id){
        try {
            teamRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id.toString());
        }
    }

    private boolean isExists(Long id) {
        return teamRepository.existsById(id);
    }

    private void updateData(Team entity, Team obj) {
        entity.setDescription(obj.getDescription());
    }

}
