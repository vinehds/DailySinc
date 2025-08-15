package com.vinehds.dailysinc.controller;

import com.vinehds.dailysinc.model.dto.DailyDTO;
import com.vinehds.dailysinc.model.entitie.Daily;
import com.vinehds.dailysinc.service.DailyService;
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
@RequestMapping("/dailies")
public class DailyController {

    private final DailyService dailyService;

    @GetMapping("/getAll")
    public ResponseEntity<List<DailyDTO>> findAll() {
        return ResponseEntity.ok().body(dailyService.getAllDailies().stream().map(DailyDTO::fillDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/getById")
    public ResponseEntity<DailyDTO> findById(@RequestParam Long id) {
        return ResponseEntity.ok().body(DailyDTO.fillDTO(dailyService.getDailyById(id)));
    }

    @PostMapping("/insert")
    public ResponseEntity<DailyDTO> insert(@RequestBody DailyDTO team) {

        Daily teamInserted = dailyService.insertDaily(team.toEntity());

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(team.getId())
                .toUri();
        return ResponseEntity.created(uri).body(DailyDTO.fillDTO(teamInserted));
    }

    @PutMapping("/update")
    public ResponseEntity<DailyDTO> update(@RequestParam Long id, @RequestBody DailyDTO obj){
        obj = DailyDTO.fillDTO(dailyService.updateDaily(id, obj.toEntity()));
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam Long id){
        dailyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
