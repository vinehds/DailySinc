package com.vinehds.dailysinc.controller;

import com.vinehds.dailysinc.model.dto.DailyDTO;
import com.vinehds.dailysinc.service.DailyService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/dailies")
public class DailyController {

    private final DailyService dailyService;

    @GetMapping ()
    public ResponseEntity<List<DailyDTO>> findAll() {
        return ResponseEntity.ok()
                .body(dailyService.getAllDailies());
    }

    @GetMapping("/byDate")
    public ResponseEntity<List<DailyDTO>> findByDate(
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate date) {
        return ResponseEntity.ok(dailyService.getAllDailiesByDate(date));
    }



    @GetMapping("/{id}")
    public ResponseEntity<DailyDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(dailyService.getDailyById(id));
    }

    @PostMapping()
    public ResponseEntity<DailyDTO> insert(@RequestBody DailyDTO daily) {

        DailyDTO dailyInserted = dailyService.insertDaily(daily);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dailyInserted.getId())
                .toUri();
        return ResponseEntity.created(uri).body(dailyInserted);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DailyDTO> update(@PathVariable Long id, @RequestBody DailyDTO obj){
        obj = dailyService.updateDaily(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        dailyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
