package com.vinehds.dailysinc.service;

import com.vinehds.dailysinc.model.dto.TeamDTO;
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


    public List<TeamDTO> getAllTeams() {
        try{
            return teamRepository.findAll().stream().map(TeamDTO::fromEntity).toList();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public TeamDTO getTeamById(Long id) {
       Optional<Team> team = teamRepository.findById(id);
       return TeamDTO.fromEntity(team.orElseThrow(() -> new RuntimeException("Team not found")));
    }

    public TeamDTO insertTeam(TeamDTO team) {
        return TeamDTO.fromEntity(teamRepository.save(team.toEntity()));
    }

    public TeamDTO updateTeam(Long id, TeamDTO obj) {
        try {
            if(!isExists(id)){
                throw new RuntimeException("Team not found");
            }

            Team entity = teamRepository.getReferenceById(id);
            updateData(entity, obj.toEntity());

            return TeamDTO.fromEntity(teamRepository.save(entity));
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void delete(Long id){
        try {
            teamRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException();
        }
    }

    private boolean isExists(Long id) {
        return teamRepository.existsById(id);
    }

    private void updateData(Team entity, Team obj) {
        entity.setDescription(obj.getDescription());
    }

}
