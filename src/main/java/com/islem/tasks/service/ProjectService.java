package com.islem.tasks.service;

import com.islem.tasks.dto.ProjectDto;
import com.islem.tasks.dto.TasksDto;

import java.util.Date;
import java.util.List;

public interface ProjectService {
    ProjectDto save(ProjectDto project);

    List<ProjectDto> findAll();

    ProjectDto findById(Integer id);

    List<ProjectDto> findAllByUserId(Integer userId);

    void delete(Integer id);
    //start

//end
    List<ProjectDto> getAllTasksByProjectsForToday(Integer userId);

}
