package com.vinehds.dailysinc.controller;

import com.vinehds.dailysinc.model.dto.TeamDTO;
import com.vinehds.dailysinc.model.entitie.Team;
import com.vinehds.dailysinc.service.TeamService;
import jakarta.servlet.ServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    @GetMapping("/getAll")
    public ResponseEntity<List<TeamDTO>> findAll(ServletResponse servletResponse) {
        return ResponseEntity.ok().body(teamService.getAllTeams().stream().map(TeamDTO::fillDTO)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/getById")
    public ResponseEntity<TeamDTO> findById(ServletResponse servletResponse, Long id) {
        return ResponseEntity.ok().body(TeamDTO.fillDTO(teamService.getTeamById(id)));
    }

    @PostMapping("/insert")
    public ResponseEntity<TeamDTO> insert(@RequestBody TeamDTO team) {

        Team teamInserted = teamService.insertTeam(team.toEntity());

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(team.getId())
                .toUri();
        return ResponseEntity.created(uri).body(TeamDTO.fillDTO(teamInserted));
    }

    @PutMapping("/update")
    public ResponseEntity<TeamDTO> update(@RequestParam Long id, @RequestBody TeamDTO obj){
        obj = TeamDTO.fillDTO(teamService.updateTeam(id, obj.toEntity()));
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam Long id){
        teamService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
