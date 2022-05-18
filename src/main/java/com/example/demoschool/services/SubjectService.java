package com.example.demoschool.services;

import com.example.demoschool.exception.SubjectNotFoundException;
import com.example.demoschool.model.Student;
import com.example.demoschool.model.Subject;
import com.example.demoschool.model.Teacher;
import com.example.demoschool.repositories.SubjectRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SubjectService {
    private final SubjectRepository  subjectRepository;
    private final StudentService studentService;
    private final TeacherService teacherService;

    public SubjectService(SubjectRepository subjectRepository,
                          @Lazy StudentService studentService,
                          @Lazy TeacherService teacherService) {
        this.subjectRepository = subjectRepository;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    // Create/Add new subject
    public Subject addSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    // Read/Get subject by id
    public Subject getSubject(Long id) {
        return subjectRepository.findById(id).orElseThrow(
                () -> new SubjectNotFoundException(id));
    }

    // Read/Get all Subjects
    public List<Subject> getAllSubjects() {
        return StreamSupport.stream(subjectRepository.findAll()
                .spliterator(),false)
                .collect(Collectors.toList());
    }

    // Update/Edit subject
    @Transactional
    public Subject updateSubject(Long subjectId, Subject subject) {
        Subject subjectToEdit = getSubject(subjectId);
        subjectToEdit.setSubjectName(subject.getSubjectName());
        subjectToEdit.setSubjectTeacher(subject.getSubjectTeacher());
        subjectToEdit.setSubjectStudents(subject.getSubjectStudents());
        return subjectToEdit;
    }

    // Delete Subject
    public Subject deleteSubject(Long id) {
        Subject subject = getSubject(id); // check if subject exists
        subjectRepository.delete(subject);
        return subject;
    }

    // Add Subject to Student
    public Subject addSubjectToStudent(Long studentId, Long subjectId) {
        Student student = studentService.getStudent(studentId);
        Subject subject = getSubject(subjectId);
        student.addSubject(subject);
        return subject;
    }

    // Remove Subject from Student
    public Subject removeSubjectFromStudent(Long studentId, Long subjectId) {
        Student student = studentService.getStudent(studentId);
        Subject subject = getSubject(subjectId);
        student.removeSubject(subject);
        return subject;
    }

    // Add Subject to Teacher
    public Subject addSubjectToTeacher(Long teacherId, Long subjectId) {
        Teacher teacher = teacherService.getTeacher(teacherId); // check if teacher exists
        Subject subject = getSubject(subjectId); // checks if subject exists
        teacher.addSubject(subject);
        return subject; // subject added to teacher
    }

    // Remove subject from Teacher
    public Subject removeSubjectFromTeacher(Long teacherId, Long subjectId) {
        Teacher teacher = teacherService.getTeacher(teacherId); // check if teacher exists
        Subject subject = getSubject(subjectId); // checks if subject exists
        teacher.removeSubject(subject);
        return subject; // subject remove from Teacher
    }
}
