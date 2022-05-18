package com.example.demoschool.exception;

import java.text.MessageFormat;

public class TeacherNotFoundException extends RuntimeException {
    public TeacherNotFoundException(Long id) {
        super(MessageFormat.format("Teacher with id: {0} not found", id));
    }
}
