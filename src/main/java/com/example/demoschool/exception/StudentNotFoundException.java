package com.example.demoschool.exception;

import java.text.MessageFormat;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(Long id) {
        super(MessageFormat.format("Could not find Student with id: {0}", id));
    }
}
