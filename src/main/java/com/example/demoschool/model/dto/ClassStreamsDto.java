package com.example.demoschool.model.dto;

import com.example.demoschool.model.ClassStreams;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ClassStreamsDto {
    private long classStreamId;
    private String streamName;
    private ClassDto classDto;
    private List<StudentDto> studentsDto = new ArrayList<>();
    private TeacherDto teacherDto;

    public long getClassStreamId() {
        return classStreamId;
    }

    public void setClassStreamId(long classStreamDtoId) {
        this.classStreamId = classStreamId;
    }

    public String getStreamName() {
        return streamName;
    }

    public void setStreamName(String streamName) {
        this.streamName = streamName;
    }

    public ClassDto getClassDto() {
        return classDto;
    }

    public void setClassDto(ClassDto classDto) {
        this.classDto = classDto;
    }

    public List<StudentDto> getStudentsDto() {
        return studentsDto;
    }

    public void setStudentsDto(List<StudentDto> studentsDto) {
        this.studentsDto = studentsDto;
    }

    public TeacherDto getTeacherDto() {
        return teacherDto;
    }

    public void setTeacherDto(TeacherDto teacherDto) {
        this.teacherDto = teacherDto;
    }

    public static ClassStreamsDto from(ClassStreams streams){
        ClassStreamsDto streamsDto = new ClassStreamsDto();
        streamsDto.setClassStreamId(streams.getStreamId());
        streamsDto.setStreamName(streams.getStreamName());
        if (Objects.nonNull(streams.getStrmClass())) {
            streamsDto.setClassDto(ClassDto.from(streams.getStrmClass()));
        }
        if (Objects.nonNull(streams.getStudents())){
            streamsDto.setStudentsDto(streams.getStudents()
                    .stream().map(StudentDto::from).collect(Collectors.toList()));
        }
       if (Objects.nonNull(streams.getTeacher())) {
           streamsDto.setTeacherDto(TeacherDto.from(streams.getTeacher()));
       }
       return streamsDto;
    }
}
