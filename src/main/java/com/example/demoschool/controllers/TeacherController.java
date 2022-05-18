package com.example.demoschool.controllers;

import com.example.demoschool.model.Teacher;
import com.example.demoschool.model.dto.TeacherDto;
import com.example.demoschool.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    // Create/Add new Teacher
    @PostMapping("/add")
    public ResponseEntity<TeacherDto> addTeacher(@RequestBody final TeacherDto teacherDto) {
        Teacher teacher = teacherService.addTeacher(Teacher.from(teacherDto));
        return new ResponseEntity<>(TeacherDto.from(teacher), HttpStatus.OK);
    }

    // Read/Get all Teachers
    @GetMapping("/all")
    public ResponseEntity<List<TeacherDto>> getAllTeachers() {
        List<Teacher> teacher = teacherService.getAllTeachers();
        List<TeacherDto> teachersDto = teacher.stream().map(TeacherDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(teachersDto, HttpStatus.OK);
    }

    // Read/Get Teacher by Id
    @GetMapping(value = "/{teacherId}")
    public ResponseEntity<TeacherDto> getTeacher(@PathVariable final Long teacherId) {
        Teacher teacher = teacherService.getTeacher(teacherId);
        return new ResponseEntity<>(TeacherDto.from(teacher),HttpStatus.OK);
    }

    // Update/Edit Teacher Entity
    @PostMapping(value = "/update/{teacherId}")
    public ResponseEntity<TeacherDto> editTeacher(@PathVariable final Long teacherId,
                                                  @RequestBody final TeacherDto teacherDto){
        Teacher editedTeacher = teacherService.updateTeacher(teacherId, Teacher.from(teacherDto));
        return new ResponseEntity<>(TeacherDto.from(editedTeacher),HttpStatus.OK);
    }

    // Delete Teacher by Id
    @DeleteMapping(value = "{id}")
    public ResponseEntity<TeacherDto> deleteTeacher(@PathVariable final Long id) {
        Teacher teacher = teacherService.deleteTeacher(id);
        return new ResponseEntity<>(TeacherDto.from(teacher),HttpStatus.OK);
    }

    //

    /*@Autowired
    private TeacherService teacherService;

    // create new teacher
    @PostMapping(path = "/addteacher")
    public response addNewTeacher(@RequestBody TeacherDTO dto) {
        return teacherService.addTeacher(dto);
    }

    // get techer by id
    @GetMapping(path = "/teacher")
    public response getTeacherById(@RequestParam(value = "id") long id) {
        return teacherService.getTeacher(id);
    }

    // get all teachers
    @GetMapping(path = "/allteachers")
    public response allTeachers() {
        return teacherService.getAllTeachers();
    }

    // delete teacher by id
    @PostMapping(path = "/del_teacher")
    public response delTeacher(@RequestParam(value = "id") long id) {
        return teacherService.delTeacher(id);
    }*/
}
