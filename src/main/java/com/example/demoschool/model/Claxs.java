package com.example.demoschool.model;

import com.example.demoschool.model.dto.ClassDto;
import com.example.demoschool.model.dto.ClassStreamsDto;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.GenerationType.SEQUENCE;

/***
 * id
 * name
 * A Class Has Many Streams | A ClassStreams Has One Class (belongs to one class)
 * (Many Streams, One Class) --> ManyToOne
 */
@Entity
@Table(name = "Claxs")
public class Claxs {
    @Id
    @Column(name = "Class_id")
    @SequenceGenerator(
            name = "class_sequence",
            sequenceName = "class_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "class_sequence"
    )
    private Long classId;

    @Column(name = "Class_name", columnDefinition = "VARCHAR(25)", nullable = false) // CHECK(className=FORM1, FORM2, FORM3, FORM4)"
    private String className;

    // relation with ClassStreams Entities
    @OneToMany(
            targetEntity = ClassStreams.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "strmClass")
    private List<ClassStreams> classStreams = new ArrayList<>();

    public void addStream(ClassStreams stream) {
        classStreams.add(stream);
    }

    public void removeStream(ClassStreams stream) {
        classStreams.remove(stream);
    }

    // empty constructor
    public Claxs() {
    }

    /*public Claxs(String className, List<ClassStreams> classStreams) {
        this.className = className;
        this.classStreams = classStreams;
    }*/

    public long getClassId() {
        return classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ClassStreams> getClassStreams() {
        return classStreams;
    }

    public void setClassStreams(List<ClassStreams> classStreams) {
        this.classStreams = classStreams;
    }

    @Override
    public String toString() {
        return "Class{" +
                "classId=" + classId +
                ", className='" + className + '\'' +
                ", classStreams=" + classStreams +
                '}';
    }

    public static Claxs from(ClassDto classDto) {
        Claxs claxs = new Claxs();
        claxs.setClassName(classDto.getClassName());
        claxs.setClassStreams(classDto.getClassStreamsDto().stream()
                .map(ClassStreams::from).collect(Collectors.toList()));
        return claxs;
    }
}
