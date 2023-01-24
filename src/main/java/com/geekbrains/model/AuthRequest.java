package com.geekbrains.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
//@Setter
public class AuthRequest {

    private String username;
    private String password;

}
