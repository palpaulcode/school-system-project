package com.example.demoschool.model;

import com.example.demoschool.model.dto.SubjectDto;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.GenerationType.SEQUENCE;

/***
 * A Subject Has Many Students | A Students Has Many Subjects --> ManyToMany
 * A Subject Has a Teacher --> (silent from this side) implemented from Teacher side
 */
@Entity
@Table(
        name = "Subjects",
        uniqueConstraints = {
                @UniqueConstraint(name = "subject_name_unique", columnNames = "SUBJECT_NAME")
        }
)
public class Subject {
    @Id
    @Column(name = "subject_id")
    @SequenceGenerator(
            name = "subject_sequence",
            sequenceName = "subject_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "subject_sequence"
    )
    private Long subjectId;

    @Column(name = "subject_name", columnDefinition = "VARCHAR(50)", nullable = false, unique = true)
    private String subjectName;

    // relation with Teacher Entity
    @ManyToOne(targetEntity = Subject.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_teacher_id")
    private Teacher subjectTeacher;

    // relation with Student Entity
    @ManyToMany(mappedBy = "subjects")
    private List<Student> subjectStudents = new ArrayList<>();

    public void addStudent(Student student) {
        subjectStudents.add(student);
    }

    public void removeStudent(Student student) {
        subjectStudents.remove(student);
    }

    public Subject() {
    }

    /*public Subject(String subjectName, Teacher subjectTeacher, List<Student> students) {
        this.subjectName = subjectName;
        this.subjectTeacher = subjectTeacher;
        this.students = students;
    }*/

    public long getSubjectId() {
        return subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Teacher getSubjectTeacher() {
        return subjectTeacher;
    }

    public void setSubjectTeacher(Teacher subjectTeacher) {
        this.subjectTeacher = subjectTeacher;
    }

    public List<Student> getSubjectStudents() {
        return subjectStudents;
    }

    public void setSubjectStudents(List<Student> students) {
        this.subjectStudents = students;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                ", subjectTeacher=" + subjectTeacher +
                ", students=" + subjectStudents +
                '}';
    }

    public static Subject from(SubjectDto subjectDto) {
        Subject subject = new Subject();
        subject.setSubjectName(subjectDto.getSubjectName());
        subject.setSubjectTeacher(Teacher.from(subjectDto.getTeacherDto()));
        subject.setSubjectStudents(subjectDto.getStudentsDto()
                .stream().map(Student::from).collect(Collectors.toList()));
        return subject;
    }
}
