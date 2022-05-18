package com.example.demoschool.model.dto;

import com.example.demoschool.model.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TeacherDto {
    private long teacherDtoId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private String email;
    private String gender;
    private String idNumber;
    private ClassStreamsDto streamDto;
    private List<SubjectDto> subjectsDto = new ArrayList<>();

    public long getTeacherDtoId() {
        return teacherDtoId;
    }

    public void setTeacherDtoId(long teacherDtoId) {
        this.teacherDtoId = teacherDtoId;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
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

    public static TeacherDto from(Teacher teacher) {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setTeacherDtoId(teacher.getTeacherId());
        teacherDto.setFirstName(teacher.getFirstName());
        teacherDto.setMiddleName(teacher.getMiddleName());
        teacherDto.setLastName(teacher.getLastName());
        teacherDto.setEmail(teacherDto.getEmail());
        teacherDto.setPhone(teacherDto.getPhone());
        teacherDto.setGender(teacher.getGender());
        teacherDto.setIdNumber(teacher.getIdNumber());
        if (Objects.nonNull(teacher.getStream())){
            teacherDto.setStreamDto(ClassStreamsDto.from(teacher.getStream()));
        }
        if (Objects.nonNull(teacher.getSubjects())){
            teacherDto.setSubjectsDto(teacher.getSubjects()
                    .stream().map(SubjectDto::from)
                    .collect(Collectors.toList()));
        }
        return teacherDto;
    }
}
