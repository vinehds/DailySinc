package com.vinehds.dailysinc.controller;

import com.vinehds.dailysinc.model.dto.DeveloperDTO;
import com.vinehds.dailysinc.model.entitie.Developer;
import com.vinehds.dailysinc.service.DeveloperService;
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
@RequestMapping("/developers")
public class DeveloperController {

    private final DeveloperService developerService;

    @GetMapping("/getAll")
    public ResponseEntity<List<DeveloperDTO>> findAll() {
        return ResponseEntity.ok().body(developerService.getAllDevelopers().stream().map(DeveloperDTO::fillDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/getById")
    public ResponseEntity<DeveloperDTO> findById(@RequestParam Long id) {
        return ResponseEntity.ok().body(DeveloperDTO.fillDTO(developerService.getDeveloperById(id)));
    }

    @PostMapping("/insert")
    public ResponseEntity<DeveloperDTO> insert(@RequestBody DeveloperDTO team) {

        Developer teamInserted = developerService.insertDeveloper(team.toEntity());

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(team.getId())
                .toUri();
        return ResponseEntity.created(uri).body(DeveloperDTO.fillDTO(teamInserted));
    }

    @PutMapping("/update")
    public ResponseEntity<DeveloperDTO> update(@RequestParam Long id, @RequestBody DeveloperDTO obj){
        obj = DeveloperDTO.fillDTO(developerService.updateDeveloper(id, obj.toEntity()));
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam Long id){
        developerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
