package com.demo.jwtdemo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorResult {
    private String code;
    private String reason;
}
