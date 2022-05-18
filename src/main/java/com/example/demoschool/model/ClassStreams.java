package com.example.demoschool.model;

import com.example.demoschool.model.dto.ClassStreamsDto;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.GenerationType.SEQUENCE;

/***
 * A ClassStreams Has A Name and ID
 * A ClassStreams Has A Class Teacher --> OneToOne
 * A ClassStreams Has Many Students | A Student Has A ClassStreams --> ManyToOne
 * A ClassStreams Has A class (belongs to a class) | A Class Has Many Streams
 * (One Class, Many Streams) --> OneToMany (implemented from class side)
 */
@Entity
@Table(name = "Class_stream")
public class ClassStreams {
    @Id
    @Column(name = "stream_id")
    @SequenceGenerator(
            name = "class_stream_sequence",
            sequenceName = "class_stream_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "sequence"
    )
    private Long streamId;

    @Column(name = "stream_name", columnDefinition = "VARCHAR(25)", nullable = false, unique = true)
    private String streamName;

    // relation with Class Entity
    @ManyToOne(targetEntity = Claxs.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "stream_class_id")
    private Claxs strmClass;

    // relation to Student entity
    @OneToMany(targetEntity = Student.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "studentStream")
    private List<Student> students;

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    // relation to Teacher Entity
    //@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JoinColumn(name = "class_teacher_id", referencedColumnName = "teacher_id")

    //@OneToOne
    //@JoinColumn(name = "teacher_id")
    @OneToOne(mappedBy = "stream", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Teacher teacher;

    public ClassStreams() {
    }

    /*public ClassStreams(String streamName, Claxs strmClass, List<Student> students, Teacher teacher) {
        this.streamName = streamName;
        this.strmClass = strmClass;
        this.students = students;
        this.teacher = teacher;
    }*/

    public long getStreamId() {
        return streamId;
    }

    public String getStreamName() {
        return streamName;
    }

    public void setStreamName(String streamName) {
        this.streamName = streamName;
    }

    public Claxs getStrmClass() {
        return strmClass;
    }

    public void setStrmClass(Claxs strmClass) {
        this.strmClass = strmClass;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "ClassStreams{" +
                "streamId=" + streamId +
                ", streamName='" + streamName + '\'' +
                ", strmClass=" + strmClass +
                ", students=" + students +
                ", teacher=" + teacher +
                '}';
    }

    public static ClassStreams from(ClassStreamsDto classStreamsDto) {
        ClassStreams streams =  new ClassStreams();
        streams.setStreamName(streams.getStreamName());
        streams.setStrmClass(Claxs.from(classStreamsDto.getClassDto()));
        streams.setStudents(classStreamsDto.getStudentsDto().stream().
                map(Student::from).collect(Collectors.toList()));
        streams.setTeacher(Teacher.from(classStreamsDto.getTeacherDto()));
        return streams;
    }
}
