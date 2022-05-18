package com.example.demoschool.exception;

import java.text.MessageFormat;

public class ClaxsNotFoundException extends RuntimeException {
    public ClaxsNotFoundException(Long id) {
        super(MessageFormat.format("Class with Id: {0} Not Found", id));
    }
}
