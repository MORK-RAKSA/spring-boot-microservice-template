package com.raksa.app.dtos.requests;

import com.raksa.app.enumz.Role;
import com.raksa.app.model.UserDetails;
import lombok.Data;

@Data
public class UserRequestDto {

    private String username;

    private String password;

    private Role role;

    private UserDetails userDetails;
}
