package com.example.demoschool.services;

import com.example.demoschool.exception.StudentNotFoundException;
import com.example.demoschool.model.ClassStreams;
import com.example.demoschool.model.Student;
import com.example.demoschool.model.Subject;
import com.example.demoschool.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ClassStreamsService streamsService;
    private final SubjectService subjectService;

    @Autowired
    public StudentService(StudentRepository studentRepository,
                          @Lazy  ClassStreamsService streamsService,
                          @Lazy SubjectService subjectService) {
        this.studentRepository = studentRepository;
        this.streamsService = streamsService;
        this.subjectService = subjectService;
    }

    // Create/add Student
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    // Read/Get Student
    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElseThrow(() ->
                new StudentNotFoundException(id));
    }

    // Read all Students
    public List<Student> getAllStudents() {
        return StreamSupport.stream(studentRepository
                .findAll().spliterator(),false)
                .collect(Collectors.toList());
    }

    // Update/Edit Student
    @Transactional
    public Student updateStudent(Long id, Student student) {
        Student updateStudent = getStudent(id); // check if student exists
        updateStudent.setFirstName(student.getFirstName());
        updateStudent.setMiddleName(student.getMiddleName());
        updateStudent.setLastName(student.getLastName());
        updateStudent.setDateOfBirth(student.getDateOfBirth());
        updateStudent.setEmail(student.getEmail());
        updateStudent.setPhone(student.getPhone());
        updateStudent.setStudentStream(student.getStudentStream());
        updateStudent.setSubjects(student.getSubjects());
        return updateStudent; // we don't have to explicitly Save b'coz it's a managed Entity
    }

    // Delete Student
    public Student deleteStudent(Long id) {
        Student studentToDelete = getStudent(id);
        studentRepository.delete(studentToDelete);
        return studentToDelete;
    }

    // Add Student to Stream
    public Student addStreamStudent(Long studentId, Long streamId) {
        ClassStreams stream = streamsService.getStream(streamId);
        Student student = getStudent(studentId);
        stream.addStudent(student);
        return student;
    }

    // Remove Student from Stream
    public Student removeStreamStudent(Long studentId, Long streamId) {
        ClassStreams stream = streamsService.getStream(streamId);
        Student student = getStudent(studentId);
        stream.removeStudent(student);
        return student;
    }


    /***
     * To be removed. To retain adding subjects to student via subject service only.
     */
    // Add Subject to Student
    public Student addStudentSubject(Long studentId, Long subjectId) {
        Subject subject = subjectService.getSubject(subjectId);
        Student student = getStudent(studentId);
        subject.addStudent(student);
        return student;
    }

    // Remove subject from Student
    public Student removeStudentSubject(Long studentId, Long subjectId) {
        Subject subject = subjectService.getSubject(subjectId);
        Student student = getStudent(studentId);
        subject.removeStudent(student);
        return student;
    }

    /*// create/add student
    public response addStudent(StudentDto dto) {
        Student st = new Student(dto.getFirstName(),
                dto.getMiddleName(), dto.getLastName(),
                dto.getPhone(), dto.getEmail());
        return new response(studentRepository.save(st));
    }

    // get all students
    public response getAllStudents() {
        return new response(studentRepository.findAll());
    }

    // get one student by id
    public response getStudent(long id) {
        return new response(studentRepository.findById(id)
                .orElseThrow(ApiRequestException::new));
    }

    // update student
    public response update(Student student, long id)
            throws ApiIdMismatchException {
        if (student.getStudentId() != id)
            throw (new ApiIdMismatchException());

        studentRepository.findById(id)
                .orElseThrow(ApiRequestException::new);

        return new response(studentRepository.save(student));
    }

    // delete student
    public response delStudent(long id) {
        studentRepository.findById(id)
                .orElseThrow(ApiRequestException::new);
        studentRepository.deleteById(id);
        return new response("deleted successfully");
    }*/

}
