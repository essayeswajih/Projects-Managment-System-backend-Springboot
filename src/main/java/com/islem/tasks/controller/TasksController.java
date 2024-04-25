package com.islem.tasks.controller;

import com.islem.tasks.dto.TasksDto;
import com.islem.tasks.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TasksController {

    @Autowired
    private TasksService tasksService;


    @PostMapping(value ="adminuser/tasks_create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TasksDto> createTasks(@RequestBody TasksDto tasksDto) {
        return new ResponseEntity<>(tasksService.save(tasksDto), HttpStatus.CREATED);
    }

    @PatchMapping(value ="adminuser/tasks_update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TasksDto> updateTasks(@RequestBody TasksDto tasksDto) {
        return new ResponseEntity<>(tasksService.save(tasksDto), HttpStatus.CREATED);
    }

    @GetMapping(value ="adminuser/tasks_all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TasksDto>> getAllTasks() {
        return new ResponseEntity<>(tasksService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value ="adminuser/tasks_{taskId:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TasksDto> getTasks(@PathVariable Integer taskId) {
        return new ResponseEntity<>(tasksService.findById(taskId), HttpStatus.OK);
    }

    @DeleteMapping(value ="adminuser/tasks_delete_{id:.+}")
    public ResponseEntity deleteTasks(@PathVariable Integer id) {
        tasksService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
