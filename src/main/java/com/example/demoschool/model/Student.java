package com.example.demoschool.model;

import com.example.demoschool.model.dto.StudentDto;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.GenerationType.SEQUENCE;

/***
 * A Student Has A ClassStreams | A ClassStreams Has Many Students.
 * (silent on this side. implemented from ClassStreams Side)
 * --> ManyToOne
 * A Student Has Many Subjects | A Subject Has Many Students --> ManyToMany
 */
@Entity // This tells Hibernate to make a table out of this class
@Table(
        name = "Students",
        uniqueConstraints = {
                @UniqueConstraint(name = "student_email_unique", columnNames = "student_email")
        }
)
public class Student {
    @Id
    @Column(name = "student_id")
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "student_sequence"
    )
    private Long studentId;

    @Column(name = "first_name", columnDefinition = "VARCHAR(25)", nullable = false)
    private String firstName;

    @Column(name = "middle_name", columnDefinition = "VARCHAR(25)", nullable = true)
    private String middleName;

    @Column(name = "last_name", columnDefinition = "VARCHAR(25)", nullable = false)
    private String lastName;

    @Column(name = "phone", columnDefinition = "VARCHAR(25)", nullable = false)
    private String phone;

    @Column(name = "student_email", columnDefinition = "VARCHAR(50)", nullable = false, unique = true)
    private String email;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Transient
    private Integer studentAge;

    // relation with ClassStreams entity
    @ManyToOne(targetEntity = ClassStreams.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_stream_id")
    private ClassStreams studentStream;

    // relation with Subjects
    @ManyToMany(targetEntity = Subject.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "Student_Subjects",joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "subject_id")}
    )
    private List<Subject> subjects = new ArrayList<>();

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public void removeSubject(Subject subject) {
        subjects.remove(subject);
    }

    public Student() {
    }

    /*public Student(String firstName,
                   String middleName,
                   String lastName,
                   String phone,
                   String email,
                   LocalDate dateOfBirth,
                   Integer studentAge,
                   ClassStreams studentStream,
                   List<Subject> subjects) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.studentAge = studentAge;
        this.studentStream = studentStream;
        this.subjects = subjects;
    }*/

    public long getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getStudentAge() {
        if (getDateOfBirth() != null)
            this.studentAge = Period.between(getDateOfBirth(), LocalDate.now()).getYears();
        else
            studentAge = -1;
        return studentAge;
    }

    /*public void setStudentAge(Integer studentAge) {

    }*/

    public ClassStreams getStudentStream() {
        return studentStream;
    }

    public void setStudentStream(ClassStreams studentStream) {
        this.studentStream = studentStream;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", studentAge=" + studentAge +
                ", studentStream=" + studentStream +
                ", subjects=" + subjects +
                '}';
    }

    public static Student from(StudentDto studentDto){
        Student student = new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setMiddleName(studentDto.getMiddleName());
        student.setLastName(studentDto.getLastName());
        student.setPhone(studentDto.getPhone());
        student.setEmail(studentDto.getEmail());
        student.setDateOfBirth(studentDto.getDateOfBirth());
        student.setStudentStream(ClassStreams.from(studentDto.getStreamDto()));
        student.setSubjects(studentDto.getSubjectsDto().stream()
                .map(Subject::from).collect(Collectors.toList()));
        return student;
    }
}
