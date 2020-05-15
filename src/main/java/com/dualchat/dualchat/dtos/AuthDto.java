package com.dualchat.dualchat.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthDto {

    private String username;

    private String password;

    private String fullName;

}
