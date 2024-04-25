package com.islem.tasks.service;

import com.islem.tasks.dto.TasksDto;

import java.util.List;

public interface TasksService {

    TasksDto save(TasksDto tasksDto);

    List<TasksDto> findAll();

    TasksDto findById(Integer id);

    List<TasksDto> findByProject(Integer projectId);

    void delete(Integer id);

}
