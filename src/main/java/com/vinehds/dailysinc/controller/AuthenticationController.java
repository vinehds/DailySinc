package com.vinehds.dailysinc.controller;

import com.vinehds.dailysinc.infra.security.TokenService;
import com.vinehds.dailysinc.model.dto.auth.AuthenticationDTO;
import com.vinehds.dailysinc.model.dto.auth.LoginResponseDTO;
import com.vinehds.dailysinc.model.dto.auth.MeDTO;
import com.vinehds.dailysinc.model.dto.auth.RegisterDTO;
import com.vinehds.dailysinc.model.entitie.Developer;
import com.vinehds.dailysinc.repository.DeveloperRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final DeveloperRepository developerRepository;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Developer) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){

        if(this.developerRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        String encriptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Developer newDeveloper = new Developer(data.email(), encriptedPassword, data.role());

        developerRepository.save(newDeveloper);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<MeDTO> me(@AuthenticationPrincipal Developer developer) {
        return ResponseEntity.ok(MeDTO.fromDeveloper(developer));
    }


}
