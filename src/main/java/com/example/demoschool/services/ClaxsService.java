package com.example.demoschool.services;

import com.example.demoschool.exception.ClaxsNotFoundException;
import com.example.demoschool.model.ClassStreams;
import com.example.demoschool.model.Claxs;
import com.example.demoschool.repositories.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClaxsService {
    private final ClassRepository classRepository;
    private final ClassStreamsService classStreamService;

    @Autowired
    public ClaxsService(ClassRepository classRepository,
                        ClassStreamsService classStreamService) {
        this.classRepository = classRepository;
        this.classStreamService = classStreamService;
    }

    // Create/Add new class
    public Claxs addClaxs(Claxs claxs) {
        return classRepository.save(claxs);
    }

    // Read/Get class by id
    public Claxs getClaxs(Long claxsId) {
        return classRepository.findById(claxsId).orElseThrow(() ->
                new ClaxsNotFoundException(claxsId));
    }

    // Read/Get all Classes
    public List<Claxs> getAllClaxses(){
        return StreamSupport.stream(classRepository.findAll()
                .spliterator(),false)
                .collect(Collectors.toList());
    }

    // Update/Edit class
    public Claxs updateClaxs(Long classId, Claxs claxs) {
        Claxs claxsToEdit = getClaxs(classId);
        claxsToEdit.setClassName(claxs.getClassName());
        claxsToEdit.setClassStreams(claxs.getClassStreams());
        return claxsToEdit;
    }

    // Delete class
    public Claxs deleteClaxs(long claxsId) {
        Claxs claxs = getClaxs(claxsId);
        classRepository.delete(claxs);
        return claxs;
    }

}
