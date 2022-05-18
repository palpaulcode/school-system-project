package com.example.demoschool.controllers;

import com.example.demoschool.model.Subject;
import com.example.demoschool.model.dto.ClassDto;
import com.example.demoschool.model.dto.SubjectDto;
import com.example.demoschool.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    // Get/Add new Subject
    @PostMapping
    public ResponseEntity<SubjectDto> addNewSubject(@RequestBody final SubjectDto subjectDto) {
        Subject subject = subjectService.addSubject(Subject.from(subjectDto));
        return new ResponseEntity<>(subjectDto.from(subject), HttpStatus.OK);
    }

    // Read/Get Subject by Id
    @GetMapping(value = "{subjectId}")
    public ResponseEntity<SubjectDto> getSubjectById(@PathVariable final Long subjectId) {
        Subject subject = subjectService.getSubject(subjectId);
        return new ResponseEntity<>(SubjectDto.from(subject),HttpStatus.OK);
    }

    // Read/Get All Subjects
    @GetMapping
    public ResponseEntity<List<SubjectDto>> getAllSubjects() {
        List<Subject> subjectsList = subjectService.getAllSubjects();
        List<SubjectDto> subjectsDto = subjectsList.stream().map(SubjectDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(subjectsDto,HttpStatus.OK);
    }

    // Update/Edit Subject
    @PutMapping(value = "{id}")
    public ResponseEntity<SubjectDto> editSubject(@PathVariable final Long id,
                                                  @RequestBody SubjectDto subjectDto) {
        Subject subject = subjectService.updateSubject(id,Subject.from(subjectDto));
        return new ResponseEntity<>(SubjectDto.from(subject),HttpStatus.OK);
    }

    // Delete Subject by Id
    @DeleteMapping(value = "{subjectId}")
    public ResponseEntity<SubjectDto> deleteSubject(@PathVariable final Long subjectId) {
        Subject subject = subjectService.deleteSubject(subjectId);
        return new ResponseEntity<>(SubjectDto.from(subject),HttpStatus.OK);
    }

    // Add Subject to Student
    @PostMapping(value = "{subjectId}/student/{studentId}/add")
    public ResponseEntity<SubjectDto> addSubjectToStudent(@PathVariable final Long subjectId,
                                                          @PathVariable final Long studentId) {
        Subject subject = subjectService.addSubjectToStudent(studentId, subjectId);
        return new ResponseEntity<>(SubjectDto.from(subject),HttpStatus.OK);
    }

    // Remove Subject from Student
    @PostMapping(value = "{subjectId}/student/{studentId}/remove")
    public ResponseEntity<SubjectDto> removeSubjectFromStudent(@PathVariable final Long subjectId,
                                                          @PathVariable final Long studentId) {
        Subject subject = subjectService.removeSubjectFromStudent(studentId, subjectId);
        return new ResponseEntity<>(SubjectDto.from(subject),HttpStatus.OK);
    }

    // Add Subject to Teacher
    @PostMapping(value = "{subjectId}/teacher/{teacherId}/add")
    public ResponseEntity<SubjectDto> addSubjectToTeacher(@PathVariable final Long subjectId,
                                                                @PathVariable final Long teacherId) {
        Subject subject = subjectService.addSubjectToTeacher(teacherId, subjectId);
        return new ResponseEntity<>(SubjectDto.from(subject),HttpStatus.OK);
    }

    // Remove Subject From Teacher
    @PostMapping(value = "{subjectId}/teacher/{teacherId}/remove")
    public ResponseEntity<SubjectDto> removeSubjectFromTeacher(@PathVariable final Long subjectId,
                                                          @PathVariable final Long teacherId) {
        Subject subject = subjectService.removeSubjectFromTeacher(teacherId, subjectId);
        return new ResponseEntity<>(SubjectDto.from(subject),HttpStatus.OK);
    }
}
