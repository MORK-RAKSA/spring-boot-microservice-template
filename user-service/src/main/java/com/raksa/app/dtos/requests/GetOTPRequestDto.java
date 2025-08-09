package com.raksa.app.dtos.requests;

import lombok.Data;

@Data
public class GetOTPRequestDto {
    private String email;
    private String secretType;
}
