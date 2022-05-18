package com.example.demoschool.model.dto;

import com.example.demoschool.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StudentDto {
    private long studentId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private String email;
    private LocalDate dateOfBirth;
    private ClassStreamsDto streamDto;
    private List<SubjectDto> subjectsDto = new ArrayList<>();

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public ClassStreamsDto getStreamDto() {
        return streamDto;
    }

    public void setStreamDto(ClassStreamsDto streamDto) {
        this.streamDto = streamDto;
    }

    public List<SubjectDto> getSubjectsDto() {
        return subjectsDto;
    }

    public void setSubjectsDto(List<SubjectDto> subjectsDto) {
        this.subjectsDto = subjectsDto;
    }

    public static StudentDto from(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setStudentId(student.getStudentId());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setMiddleName(student.getMiddleName());
        studentDto.setLastName(student.getLastName());
        if (Objects.nonNull(student.getStudentStream())){
            studentDto.setStreamDto(ClassStreamsDto
                    .from(student.getStudentStream()));
        }
        if (Objects.nonNull(student.getSubjects())) {
            studentDto.setSubjectsDto(student.getSubjects()
                    .stream().map(SubjectDto::from)
                    .collect(Collectors.toList()));
        }
        studentDto.setEmail(student.getEmail());
        studentDto.setPhone(student.getPhone());
        studentDto.setDateOfBirth(student.getDateOfBirth());
        return studentDto;
    }
}
