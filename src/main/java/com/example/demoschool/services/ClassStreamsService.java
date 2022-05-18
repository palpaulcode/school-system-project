package com.example.demoschool.services;

import com.example.demoschool.exception.ClassStreamNotFoundException;
import com.example.demoschool.exception.StudentAlreadyAssignedStreamException;
import com.example.demoschool.exception.TeacherAlreadyIsClassTeacherException;
import com.example.demoschool.model.ClassStreams;
import com.example.demoschool.model.Claxs;
import com.example.demoschool.model.Student;
import com.example.demoschool.model.Teacher;
import com.example.demoschool.repositories.ClassRepository;
import com.example.demoschool.repositories.ClassStreamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClassStreamsService {
    private final ClassStreamsRepository classStreamsRepository;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final ClaxsService claxsService;

    @Autowired
    public ClassStreamsService(ClassStreamsRepository classStreamsRepository,
                               @Lazy StudentService studentService,
                               @Lazy TeacherService teacherService,
                               @Lazy ClaxsService claxsService) {
        this.classStreamsRepository = classStreamsRepository;
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.claxsService = claxsService;
    }

    // Create/Add new Stream
    public ClassStreams addStream(ClassStreams classStream) {
        return classStreamsRepository.save(classStream);
    }

    // Read/Get Stream
    public ClassStreams getStream(Long id) {
        return classStreamsRepository.findById(id).orElseThrow(() ->
                new ClassStreamNotFoundException(id));
    }

    // Read/Get all Streams
    public List<ClassStreams> getAllStreams() {
        return StreamSupport.stream(classStreamsRepository
                .findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    // Update/Edit Stream
    public ClassStreams editStream(Long streamId, ClassStreams stream) {
        ClassStreams streamToEdit = getStream(streamId);
        streamToEdit.setStreamName(stream.getStreamName());
        streamToEdit.setStrmClass(stream.getStrmClass());
        streamToEdit.setStudents(stream.getStudents());
        streamToEdit.setTeacher(stream.getTeacher());
        return streamToEdit;
    }

    // Delete Stream
    public ClassStreams deleteStream(Long streamId) {
        ClassStreams stream = getStream(streamId);
        classStreamsRepository.delete(stream);
        return stream;
    }

    // Add/Set Student to Stream
    public ClassStreams addStudentToStream(Long studentId, Long streamId) {
        Student student = studentService.getStudent(studentId); // check if student with that Id exists
        ClassStreams streams = getStream(streamId); // check if stream with given Id exists
        if (Objects.nonNull(student.getStudentStream()))
            throw new StudentAlreadyAssignedStreamException(studentId,
                    student.getStudentStream().getStreamId());
        student.setStudentStream(streams);
        return streams; // stream student was added to
    }

    // Set/Add Stream ClassTeacher
    public ClassStreams addStreamClassTeacher(Long teacherId, Long streamId) {
        Teacher teacher = teacherService.getTeacher(teacherId);
        ClassStreams stream = getStream(streamId);
        if (Objects.nonNull(teacher.getStream()))
            throw new TeacherAlreadyIsClassTeacherException(
                    teacherId, teacher.getStream().getStreamId());

        teacher.setStream(stream);
        return stream;
    }

    // Add Stream to class
    public ClassStreams addStreamToClass(Long classId, Long streamId) {
        Claxs claxs = claxsService.getClaxs(classId);
        ClassStreams stream = getStream(streamId);
        claxs.addStream(stream);
        return stream;
    }

    // Remove Stream from class
    public ClassStreams removeStreamFromClass(Long classId, Long streamId) {
        Claxs claxs = claxsService.getClaxs(classId);
        ClassStreams stream = getStream(streamId);
        claxs.removeStream(stream);
        return stream;
    }
}
