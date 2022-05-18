package com.example.demoschool.exception;

import java.text.MessageFormat;

public class SubjectNotFoundException extends RuntimeException {
    public SubjectNotFoundException(Long id) {
        super(MessageFormat.format("Subject with id: {0} not found", id));
    }
}
