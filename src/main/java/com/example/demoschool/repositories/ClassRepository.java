package com.example.demoschool.repositories;

import com.example.demoschool.model.Claxs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Claxs, Long> {
}
