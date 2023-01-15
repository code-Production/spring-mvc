package com.geekbrains.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ProductErrorResponse {

    private int status;
    private String message;
    private LocalDateTime createdAt;

}
