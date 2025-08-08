package com.raksa.app.dtos.responses;

import com.raksa.app.enumz.Role;
import lombok.Data;

@Data
public class UserResponseDto {
    private String id;
    private String username;
    private String password;
    private Role role;
}
