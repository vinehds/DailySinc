package com.vinehds.dailysinc.controller;

import com.vinehds.dailysinc.model.dto.DeveloperDTO;
import com.vinehds.dailysinc.service.DeveloperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/developers")
public class DeveloperController {

    private final DeveloperService developerService;

    @GetMapping()
    public ResponseEntity<List<DeveloperDTO>> findAll() {
        return ResponseEntity.ok().body(developerService.getAllDevelopers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeveloperDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(developerService.getDeveloperById(id));
    }

    @PostMapping()
    public ResponseEntity<DeveloperDTO> insert(@RequestBody DeveloperDTO developer) {

        DeveloperDTO developerInserted = developerService.insertDeveloper(developer);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(developerInserted.getId())
                .toUri();
        return ResponseEntity.created(uri).body(developerInserted);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeveloperDTO> update(@PathVariable Long id, @RequestBody DeveloperDTO obj){
        obj = developerService.updateDeveloper(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        developerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
