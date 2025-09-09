package com.vinehds.dailysinc.controller;

import com.vinehds.dailysinc.model.dto.TeamDTO;
import com.vinehds.dailysinc.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teams")
public class TeamController {   

    private final TeamService teamService;

    @GetMapping()
    public ResponseEntity<List<TeamDTO>> findAll() {
        return ResponseEntity.ok().body(teamService.getAllTeams());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(teamService.getTeamById(id));
    }

    @PostMapping()
    public ResponseEntity<TeamDTO> insert(@RequestBody TeamDTO dto) {

        TeamDTO teamInserted = teamService.insertTeam(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(teamInserted.getId())
                .toUri();
        return ResponseEntity.created(uri).body(teamInserted);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamDTO> update(@PathVariable Long id, @RequestBody TeamDTO obj){
        obj = teamService.updateTeam(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        teamService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
