package com.example.demoschool.exception;

import java.text.MessageFormat;

public class TeacherAlreadyIsClassTeacherException extends RuntimeException {
    public TeacherAlreadyIsClassTeacherException(Long teacherId, Long classId) {
        super(MessageFormat.format("Teacher with id: {0} is already class Teacher of class {1}", teacherId, classId));
    }
}
