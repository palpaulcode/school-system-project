package com.example.demoschool.repositories;

import com.example.demoschool.model.ClassStreams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassStreamsRepository extends JpaRepository<ClassStreams, Long> {
}
