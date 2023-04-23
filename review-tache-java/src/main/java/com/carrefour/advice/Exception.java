package com.carrefour.advice;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Exception {
    private int status;
    private String error;
    private String message;
}
