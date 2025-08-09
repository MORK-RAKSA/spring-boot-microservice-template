package com.raksa.app.dtos.requests;

import lombok.Data;

@Data
public class UserRegisterRequestDto {
    private String username;
    private String email;
    private String numberPhone;
    private String password;
}
