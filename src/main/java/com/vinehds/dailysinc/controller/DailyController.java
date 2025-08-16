package com.vinehds.dailysinc.controller;

import com.vinehds.dailysinc.model.dto.DailyDTO;
import com.vinehds.dailysinc.model.entitie.Daily;
import com.vinehds.dailysinc.service.DailyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/dailies")
public class DailyController {

    private final DailyService dailyService;

    @GetMapping()
    public ResponseEntity<List<DailyDTO>> findAll() {
        return ResponseEntity.ok()
                .body(dailyService.getAllDailies().stream().map(DailyDTO::fromEntity).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DailyDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(DailyDTO.fromEntity(dailyService.getDailyById(id)));
    }

    @PostMapping()
    public ResponseEntity<DailyDTO> insert(@RequestBody DailyDTO daily) {

        Daily dailyInserted = dailyService.insertDaily(daily.toEntity());

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dailyInserted.getId())
                .toUri();
        return ResponseEntity.created(uri).body(DailyDTO.fromEntity(dailyInserted));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DailyDTO> update(@PathVariable Long id, @RequestBody DailyDTO obj){
        obj = DailyDTO.fromEntity(dailyService.updateDaily(id, obj.toEntity()));
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        dailyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
