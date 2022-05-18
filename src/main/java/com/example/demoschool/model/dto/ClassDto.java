package com.example.demoschool.model.dto;

import com.example.demoschool.model.Claxs;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ClassDto {
    private long classId;
    private String className;
    private List<ClassStreamsDto> classStreamsDto;

    public long getClassId() {
        return classId;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ClassStreamsDto> getClassStreamsDto() {
        return classStreamsDto;
    }

    public void setClassStreamsDto(List<ClassStreamsDto> classStreamsDto) {
        this.classStreamsDto = classStreamsDto;
    }

    public static ClassDto from(Claxs claxs){
        ClassDto classDto = new ClassDto();
        classDto.setClassId(claxs.getClassId());
        classDto.setClassName(claxs.getClassName());
        if (Objects.nonNull(claxs.getClassStreams())) {
            //classDto.setClassStreamsDto(ClassStreamsDto.from(claxs.getClassStreams()));
            classDto.setClassStreamsDto(claxs.getClassStreams()
                    .stream().map(ClassStreamsDto::from)
                    .collect(Collectors.toList()));
        }
        return classDto;
    }
}
