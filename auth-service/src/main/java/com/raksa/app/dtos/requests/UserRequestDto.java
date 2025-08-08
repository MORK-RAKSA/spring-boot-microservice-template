package com.raksa.app.dtos.requests;

import com.raksa.app.enumz.Role;
import lombok.Data;

@Data
public class UserRequestDto {

    private String username;

    private String password;
}
