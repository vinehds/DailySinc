package com.vinehds.dailysinc.model.dto.auth;

import com.vinehds.dailysinc.model.enums.UserRole;

public record RegisterDTO(String email, String password, UserRole role) {
}
