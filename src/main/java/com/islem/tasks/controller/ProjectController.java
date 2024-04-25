package com.islem.tasks.controller;


import com.islem.tasks.dto.ProjectDto;
import com.islem.tasks.dto.TasksDto;
import com.islem.tasks.service.ProjectService;
import com.islem.tasks.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/projects")
@CrossOrigin(origins = "*", maxAge = 3600)

public class ProjectController {
    @Autowired
    private TasksService tasksService;

    @Autowired
    private ProjectService projectService;

    @PostMapping(value = "adminuser/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto) {
        return new ResponseEntity<>(projectService.save(projectDto), HttpStatus.CREATED);
    }

    @PatchMapping(value ="adminuser/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectDto> updateProject(@RequestBody ProjectDto projectDto) {
        return new ResponseEntity<>(projectService.save(projectDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "adminuser/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        return new ResponseEntity<>(projectService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value ="adminuser/tasks/{id:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TasksDto>> getAllTasksByProjectsId(@PathVariable Integer id) {
        return new ResponseEntity<>(tasksService.findByProject(id), HttpStatus.OK);
    }

    @GetMapping(value = "adminuser/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProjectDto>> getAllProjectsByUserId(@PathVariable Integer id) {
        return new ResponseEntity<>(projectService.findAllByUserId(id), HttpStatus.OK);
    }

    @GetMapping(value = "adminuser/{id:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectDto> getProject(@PathVariable Integer id) {
        return new ResponseEntity<>(projectService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping(value ="adminuser/delete/{id:.+}")
    public ResponseEntity deleteProject(@PathVariable Integer id) {
        projectService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "adminuser/tasks/today/{userId:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TasksDto>> getAllTasksByProjectsForToday(@PathVariable Integer userId) {
        return new ResponseEntity(projectService.getAllTasksByProjectsForToday(userId), HttpStatus.OK);
    }


}
