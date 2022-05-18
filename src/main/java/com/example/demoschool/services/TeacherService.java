package com.example.demoschool.services;

import com.example.demoschool.exception.TeacherAlreadyIsClassTeacherException;
import com.example.demoschool.exception.TeacherNotFoundException;
import com.example.demoschool.model.ClassStreams;
import com.example.demoschool.model.Subject;
import com.example.demoschool.model.Teacher;
import com.example.demoschool.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final SubjectService subjectService;
    private final ClassStreamsService classStreamsService;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository,
                          SubjectService subjectService,
                          ClassStreamsService classStreamsService) {
        this.teacherRepository = teacherRepository;
        this.subjectService = subjectService;
        this.classStreamsService = classStreamsService;
    }

    // Create/Add new Teacher
    public Teacher addTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    // Read/Get Teacher
    public Teacher getTeacher(Long id) {
        return teacherRepository.findById(id).orElseThrow(
                () -> new TeacherNotFoundException(id));
    }

    // Read/Get all Teachers
    public List<Teacher> getAllTeachers() {
        return StreamSupport.stream(teacherRepository.findAll()
                .spliterator(), false).collect(Collectors.toList());
    }

    // Update/Edit Teacher
    @Transactional
    public Teacher updateTeacher(Long teacherId, Teacher teacher) {
        Teacher teacherToEdit = getTeacher(teacherId); // check if teacher with given Id exists
        teacherToEdit.setFirstName(teacher.getFirstName());
        teacherToEdit.setMiddleName(teacher.getMiddleName());
        teacherToEdit.setLastName(teacher.getLastName());
        teacherToEdit.setEmail(teacher.getEmail());
        teacherToEdit.setPhone(teacher.getPhone());
        teacherToEdit.setGender(teacher.getGender());
        teacherToEdit.setStream(teacher.getStream());
        teacherToEdit.setIdNumber(teacher.getIdNumber());
        teacherToEdit.setSubjects(teacher.getSubjects());
        return teacherToEdit;
    }

    // Delete Teacher
    public Teacher deleteTeacher(Long id) {
        Teacher teacherToDelete = getTeacher(id); // checks if teacher with given Id exists
        teacherRepository.deleteById(id);
        return teacherToDelete;
    }

    // Remove Teacher as Stream ClassTeacher
    /*public ClassStreams removeAsStreamClassTeacher(Long teacherId, Long streamId) {
        Teacher teacher = getTeacher(teacherId);
        ClassStreams stream = classStreamsService.getStream(streamId);
        teacherRepository.
    }*/


    /*// add one techer
    public response addTeacher(TeacherDTO dto) {
        Teacher t = new Teacher(dto.getFirstName(), dto.getMiddleName(),
                dto.getLastName(), dto.getPhone(), dto.getEmail(),
                dto.getGender(), dto.getSalutation());

        return new response(teacherRepository.save(t));
    }

    // get one teacher (by id)
    public response getTeacher(long id) {
        return new response(teacherRepository.findById(id));
    }

    // get all teachers
    public response getAllTeachers() {
        return new response(teacherRepository.findAll());
    }

    // delete teacher
    public response delTeacher(long id) {
        teacherRepository.deleteById(id);
        return new response("deleted successfully");
    }*/

}
