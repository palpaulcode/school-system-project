package com.example.demoschool.exception;

import java.text.MessageFormat;

public class ClassStreamNotFoundException extends RuntimeException{
    public ClassStreamNotFoundException(Long id) {
        super(MessageFormat.format("Class Stream With Id: {0} Not Found", id));
    }
}
