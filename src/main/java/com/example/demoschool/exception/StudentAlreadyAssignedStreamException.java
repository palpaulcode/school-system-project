package com.example.demoschool.exception;

import java.text.MessageFormat;

public class StudentAlreadyAssignedStreamException extends RuntimeException{
    public StudentAlreadyAssignedStreamException(Long studentId, Long streamId) {
        super(MessageFormat.format("Student {0} already assigned Stream {1}", studentId, streamId));
    }
}
