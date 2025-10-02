package com.vinehds.dailysinc.model.dto.auth;

import com.vinehds.dailysinc.model.entitie.Developer;

public record MeDTO(Long id, String name, String email, String role) {
    public static MeDTO fromDeveloper(Developer dev) {
        return new MeDTO(
                dev.getId(),
                dev.getName(),
                dev.getEmail(),
                dev.getUserRole().name()
        );
    }
}

