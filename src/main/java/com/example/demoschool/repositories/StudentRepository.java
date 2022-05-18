package com.example.demoschool.repositories;

import com.example.demoschool.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}

/*
 *
 * This repository holds student records
 *
 * This will be AUTO IMPLEMENTED by Spring into a
 * Bean called studentRepository
 *
 * CRUD refers to Create, Read, Update, Delete
 * */
