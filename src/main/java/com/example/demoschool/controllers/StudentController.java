package com.example.demoschool.controllers;

import com.example.demoschool.model.Student;
import com.example.demoschool.model.dto.StudentDto;
import com.example.demoschool.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Create/add new Student
    @PostMapping("/add")
    public ResponseEntity<StudentDto> addStudent(@RequestBody final StudentDto studentDto) {
        Student student = studentService.addStudent(Student.from(studentDto));
        return new ResponseEntity<>(StudentDto.from(student), HttpStatus.OK);
    }

    //Read/Get all Students
    @GetMapping("/all")
    public ResponseEntity<List<StudentDto>> getStudents() {
        List<Student> students = studentService.getAllStudents();
        List<StudentDto> studentsDto = students.stream().map(StudentDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(studentsDto,HttpStatus.OK);
    }

    // Read/Get student by id
    @GetMapping(value = "{id}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable final Long id) {
        Student student = studentService.getStudent(id);
        return new ResponseEntity<>(StudentDto.from(student), HttpStatus.OK);
    }

    // Update Student
    @PostMapping("/update/{studentId}")
    public ResponseEntity<StudentDto> editStudent(@PathVariable final Long id,
                                                  @RequestBody final StudentDto studentDto){
        Student editedStudent = studentService.updateStudent(id,Student.from(studentDto));
        return new ResponseEntity<>(StudentDto.from(editedStudent), HttpStatus.OK);
    }

    // Delete Student by Id
    @DeleteMapping(value = "{id}")
    public ResponseEntity<StudentDto> deleteStudent(@PathVariable final Long id) {
        Student student = studentService.deleteStudent(id);
        return new ResponseEntity<>(StudentDto.from(student),HttpStatus.OK);
    }

    // Add Student to Stream
    @PostMapping(value = "{studentId}/stream/{streamId}/add")
    public ResponseEntity<StudentDto> addStudentToStream(@PathVariable final Long studentId,
                                                           @PathVariable final Long streamId) {
        Student student = studentService.addStreamStudent(studentId, streamId);
        return new ResponseEntity<>(StudentDto.from(student),HttpStatus.OK);
    }

    // Remove Student from Stream
    @PostMapping(value = "{studentId}/stream/{streamId}/remove")
    public ResponseEntity<StudentDto> removeStudentFromStream(@PathVariable final Long studentId,
                                                              @PathVariable final Long streamId) {
        Student student = studentService.removeStreamStudent(studentId,streamId);
        return new ResponseEntity<>(StudentDto.from(student),HttpStatus.OK);
    }

    // Add Subject to Student
    @PostMapping(value = "{studentId}/subject/{subjectId}/add")
    public ResponseEntity<StudentDto> addStudentSubject(@PathVariable final Long studentId,
                                                        @PathVariable final Long subjectId) {
        Student student = studentService.addStudentSubject(studentId, subjectId);
        return new ResponseEntity<>(StudentDto.from(student),HttpStatus.OK);
    }

    // Remove Subject from Student
    @PostMapping(value = "{studentId}/subject/{subjectId}/remove")
    public ResponseEntity<StudentDto> removeStudentSubject(@PathVariable final Long studentId,
                                                          @PathVariable final  Long subjectId) {
        Student student = studentService.removeStudentSubject(studentId, subjectId);
        return new ResponseEntity<>(StudentDto.from(student), HttpStatus.OK);
    }

    /*// create and save new student
    @PostMapping(path = "/student/add")
    @ResponseStatus(HttpStatus.CREATED)
    public response addNewStudent(@RequestBody StudentDTO dto) {
        return studentService.addStudent(dto);
    }

    // get one student by id
    @GetMapping(path = "/student/{id}")
    public response getStudent(@PathVariable("id") long id) {
        return studentService.getStudent(id);
    }

    // get all students
    @GetMapping(path = "/student/all")
    public response getAllStudents() {
        return studentService.getAllStudents();
    }

    // Update student record
    @PutMapping("/student/update/{id}")
    public response updateStudent(@RequestBody Student student,
                                  @PathVariable long id)
            throws ApiIdMismatchException {
        return studentService.update(student, id);
    }

    // Delete student
    @DeleteMapping(path = "/student/del/{id}")
    public response delStudent(@PathVariable("id") long id) {
        return studentService.delStudent(id);
    }*/
}
