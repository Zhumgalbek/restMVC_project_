package com.peaksoft.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_seq")
    @SequenceGenerator(name = "group_seq", sequenceName = "group_seq", allocationSize = 1)
    private Long id;

    @Column(length = 100000, name = "group_name")
    private String groupName;

    @Column(name = "date_of_start")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfStart;

    @Column(length = 100000, name = "image")
    private String image;






    @ManyToMany(cascade = {MERGE, REFRESH, DETACH},fetch = FetchType.LAZY)
    private List<Course> courses;

    public void addCourse(Course course){
        if (courses==null){
            courses=new ArrayList<>();
        }
        courses.add(course);
    }

    @OneToMany(cascade = {MERGE, PERSIST, DETACH, REFRESH, REMOVE}, fetch = FetchType.LAZY, mappedBy = "groups")
    private List<Student> students;

    public void addStudent(Student student){
        if (students==null){
            students=new ArrayList<>();
        }
        students.add(student);
    }

    public void assignStudent(Student student){
        if (students==null){
            students=new ArrayList<>();
        }
        students.add(student);
    }
    public void remove(Course course){
        this.courses.remove(course);
        course.getGroups().remove(this);
    }

}
