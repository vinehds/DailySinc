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

    @GetMapping()
    public ResponseEntity<List<DeveloperDTO>> findAll() {
        return ResponseEntity.ok()
                .body(developerService.getAllDevelopers().stream().map(DeveloperDTO::fromEntity).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeveloperDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(DeveloperDTO.fromEntity(developerService.getDeveloperById(id)));
    }

    @PostMapping()
    public ResponseEntity<DeveloperDTO> insert(@RequestBody DeveloperDTO developer) {

        Developer developerInserted = developerService.insertDeveloper(developer.toEntity());

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(developerInserted.getId())
                .toUri();
        return ResponseEntity.created(uri).body(DeveloperDTO.fromEntity(developerInserted));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeveloperDTO> update(@PathVariable Long id, @RequestBody DeveloperDTO obj){
        obj = DeveloperDTO.fromEntity(developerService.updateDeveloper(id, obj.toEntity()));
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        developerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
