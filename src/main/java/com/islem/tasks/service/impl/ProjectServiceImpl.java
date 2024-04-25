package com.islem.tasks.service.impl;

import com.islem.tasks.dto.ProjectDto;
import com.islem.tasks.exception.InvalidEntityException;
import com.islem.tasks.exception.EntityNotFoundException;
import com.islem.tasks.exception.ErrorCodes;
import com.islem.tasks.repository.ProjectRepository;
import com.islem.tasks.service.ProjectService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.islem.tasks.validators.ProjectValidator;


import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

@Service
@Slf4j



public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public ProjectDto save(ProjectDto project) {
        List<String> errors = ProjectValidator.validatePoject(project);
        if (!errors.isEmpty()) {
            log.error("Project is not valid {}", project);
            throw new InvalidEntityException("Project is not valid", ErrorCodes.PROJECT_NOT_VALID, errors);
        }
        return ProjectDto.fromEntity(projectRepository.save(ProjectDto.toEntity(project)));
    }
    @Override
    public List<ProjectDto> findAll() {
        return projectRepository.findAll().stream()
                .map(ProjectDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDto findById(Integer id) {
        if (id == null) {
            log.error("Project id is null");
            return null;
        }
        return projectRepository.findById(id).map(ProjectDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("No Project found with ID = " + id,ErrorCodes.USER_NOT_FOUND));
    }
    @Override
    public List<ProjectDto> findAllByUserId(Integer userId) {
        return projectRepository.findProjectByUserId(userId).stream()
                .map(ProjectDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Project id is null");
            return;
        }
        projectRepository.deleteById(id);
    }

    @Override
    public List<ProjectDto> getAllTasksByProjectsForToday(Integer userId) {
        return projectRepository.getAllTasksByProjectsForToday(ZonedDateTime.now().withHour(0).withMinute(0),
                        ZonedDateTime.now().withHour(23).withMinute(59), userId)
                .stream()
                .map(ProjectDto::fromEntity)
                .collect(Collectors.toList());
    }
    //start

    //end
}
