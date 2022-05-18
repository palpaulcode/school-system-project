package com.example.demoschool.controllers;

import com.example.demoschool.model.ClassStreams;
import com.example.demoschool.model.dto.ClassStreamsDto;
import com.example.demoschool.services.ClassStreamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/streams")
public class ClassStreamController {
    private final ClassStreamsService classStreamsService;

    @Autowired
    public ClassStreamController(ClassStreamsService classStreamsService) {
        this.classStreamsService = classStreamsService;
    }

    // Create/Add new Class Stream
    @PostMapping
    public ResponseEntity<ClassStreamsDto> addNewStream(
            @RequestBody final ClassStreamsDto classStreamsDto) {
        ClassStreams stream = classStreamsService.addStream(ClassStreams.from(classStreamsDto));
        return new ResponseEntity<>(ClassStreamsDto.from(stream), HttpStatus.OK);
    }

    // Read/Get ClassStream by Id
    /***
     * parameters -> CLass Id(Class Where the stream belongs), Stream ID
     * Check if class exist else throw error
     * check if stream exists in the given class or else throw error
     */
    @GetMapping(value = "{id}")
    public ResponseEntity<ClassStreamsDto> getClassStream(@PathVariable final Long id) {
        ClassStreams stream = classStreamsService.getStream(id);
        return new ResponseEntity<>(ClassStreamsDto.from(stream),HttpStatus.OK);
    }

    //Read/Get All Streams
    @GetMapping
    public ResponseEntity<List<ClassStreamsDto>> getAllStreams() {
        List<ClassStreams> streams = classStreamsService.getAllStreams();
        List<ClassStreamsDto> streamsDto = streams.stream().map(ClassStreamsDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(streamsDto,HttpStatus.OK);
    }

    // Update/Edit Stream
    @PutMapping(value = "{streamId}")
    public ResponseEntity<ClassStreamsDto> editClassStream(@PathVariable final Long streamId,
                                                           @RequestBody final ClassStreamsDto classStreamDto) {
        ClassStreams stream = classStreamsService.editStream(streamId, ClassStreams.from(classStreamDto));
        return new ResponseEntity<>(ClassStreamsDto.from(stream),HttpStatus.OK);
    }

    // Delete Stream
    @DeleteMapping(value = "{id}")
    public ResponseEntity<ClassStreamsDto> deleteClassStream(@PathVariable final Long streamId) {
        ClassStreams stream = classStreamsService.deleteStream(streamId);
        return new ResponseEntity<>(ClassStreamsDto.from(stream),HttpStatus.OK);
    }

    // Add/Set Student to Stream
    @PostMapping(value = "{studentId}/addstudent/{streamId}")
    public ResponseEntity<ClassStreamsDto> addStudentToStream(@PathVariable final Long studentId,
                                                              @PathVariable final Long streamId) {
        ClassStreams stream = classStreamsService.addStudentToStream(studentId, streamId);
        return new ResponseEntity<>(ClassStreamsDto.from(stream),HttpStatus.OK);
    }

    // Set/Add Stream Classteacher
    @PostMapping(value = "{teacherId}/add/classteacher/{streamId}")
    public ResponseEntity<ClassStreamsDto> addStreamClassTeacher(@PathVariable final Long teacherId,
                                                                 @PathVariable final Long streamId) {
        ClassStreams stream = classStreamsService.addStreamClassTeacher(teacherId,streamId);
        return new ResponseEntity<>(ClassStreamsDto.from(stream),HttpStatus.OK);
    }

    // Add Stream to Class
    @PostMapping(value = "{classId}/add/stream/{streamId}")
    public ResponseEntity<ClassStreamsDto> addStreamToClass(@PathVariable final Long classId,
                                                            @PathVariable final Long streamId) {
        ClassStreams stream = classStreamsService.addStreamToClass(classId,streamId);
        return new ResponseEntity<>(ClassStreamsDto.from(stream),HttpStatus.OK);
    }

    // Remove Stream from Class
    @PostMapping(value = "{classId}/remove/stream/{streamId}")
    public ResponseEntity<ClassStreamsDto> removeStreamFromClass(@PathVariable final Long classId,
                                                                 @PathVariable final Long streamId) {
        ClassStreams stream = classStreamsService.removeStreamFromClass(classId,streamId);
        return new ResponseEntity<>(ClassStreamsDto.from(stream),HttpStatus.OK);
    }
}
