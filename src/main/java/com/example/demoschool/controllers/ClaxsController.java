package com.example.demoschool.controllers;

import com.example.demoschool.model.Claxs;
import com.example.demoschool.model.dto.ClassDto;
import com.example.demoschool.services.ClaxsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/classes")
public class ClaxsController {
    private final ClaxsService claxsService;

    @Autowired
    public ClaxsController(ClaxsService claxsService) {
        this.claxsService = claxsService;
    }

    // Create/Add new class
    @PostMapping("/add")
    public ResponseEntity<ClassDto> addNewClass(@RequestBody final ClassDto classDto) {
        Claxs claxs = claxsService.addClaxs(Claxs.from(classDto));
        return new ResponseEntity<>(ClassDto.from(claxs), HttpStatus.OK);
    }

    // Read/Get Class by Id
    @GetMapping("{classId}")
    public ResponseEntity<ClassDto> getClass(@PathVariable final Long classId) {
        Claxs claxs = claxsService.getClaxs(classId);
        return new ResponseEntity<>(ClassDto.from(claxs),HttpStatus.OK);
    }

    // Read/Get all classes
    @GetMapping()
    public ResponseEntity<List<ClassDto>> getAllClasses(){
        List<Claxs> claxsList = claxsService.getAllClaxses();
        List<ClassDto> classesDto = claxsList.stream().map(ClassDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(classesDto,HttpStatus.OK);
    }

    // Update/Edit Class
    @PostMapping("{classId}")
    public ResponseEntity<ClassDto> editClass(@PathVariable final Long classId,
                                              @RequestBody final ClassDto classDto) {
        Claxs editedClaxs = claxsService.updateClaxs(classId, Claxs.from(classDto));
        return new ResponseEntity<>(ClassDto.from(editedClaxs),HttpStatus.OK);
    }

    // Delete Class Entity
    @DeleteMapping(value = "{classId}")
    public ResponseEntity<ClassDto> deleteClass(@PathVariable final Long classId) {
        Claxs claxs = claxsService.deleteClaxs(classId);
        return new ResponseEntity<>(ClassDto.from(claxs),HttpStatus.OK);
    }


}
