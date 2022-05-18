package com.example.demoschool.model.dto;

import com.example.demoschool.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SubjectDto {
    private long subjectDtoId;
    private String subjectName;
    private TeacherDto teacherDto;
    private List<StudentDto> studentsDto = new ArrayList<>();

    public long getSubjectDtoId() {
        return subjectDtoId;
    }

    public void setSubjectDtoId(long subjectDtoId) {
        this.subjectDtoId = subjectDtoId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public TeacherDto getTeacherDto() {
        return teacherDto;
    }

    public void setTeacherDto(TeacherDto teacherDto) {
        this.teacherDto = teacherDto;
    }

    public List<StudentDto> getStudentsDto() {
        return studentsDto;
    }

    public void setStudentsDto(List<StudentDto> studentsDto) {
        this.studentsDto = studentsDto;
    }

    public static SubjectDto from(Subject subject) {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setSubjectDtoId(subject.getSubjectId());
        subjectDto.setSubjectName(subject.getSubjectName());
        if (Objects.nonNull(subject.getSubjectTeacher())) {
            subjectDto.setTeacherDto(TeacherDto.from(subject.getSubjectTeacher()));
        }
        if (Objects.nonNull(subject.getSubjectStudents())) {
            subjectDto.setStudentsDto(subject.getSubjectStudents()
                    .stream().map(StudentDto::from)
                    .collect(Collectors.toList()));
        }

        return subjectDto;
    }
}
