package com.islem.tasks.service.impl;

import com.islem.tasks.dto.ProjectDto;
import com.islem.tasks.dto.TasksDto;
import com.islem.tasks.dto.UserDto;
import com.islem.tasks.exception.InvalidEntityException;
import com.islem.tasks.exception.EntityNotFoundException;
import com.islem.tasks.exception.ErrorCodes;
import com.islem.tasks.entity.Project;
import com.islem.tasks.entity.Tasks;
import com.islem.tasks.repository.ProjectRepository;
import com.islem.tasks.repository.TasksRepository;
import com.islem.tasks.service.TasksService;
import com.islem.tasks.validators.TasksValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j

public class TasksServiceImpl implements TasksService {


    @Autowired
    private TasksRepository tasksRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public TasksDto save(TasksDto tasksDto) {
        List<String> errors = TasksValidator.validateTasks(tasksDto);
        if (!errors.isEmpty()) {
            log.error("Tasks is not valid {}", tasksDto);
            throw new InvalidEntityException("Tasks is not valid", ErrorCodes.TASK_NOT_VALID, errors);
        }
        return TasksDto.fromEntity(tasksRepository.save(TasksDto.toEntity(tasksDto)));
    }

    @Override
    public List<TasksDto> findAll() {
        return tasksRepository.findAll().stream()
                .map(TasksDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public TasksDto findById(Integer id) {
        if (id == null) {
            log.error("User id is null");
            return null;
        }
        final Integer ProjectId = projectRepository.findProjectByTasksId(id);
        Project Project = new Project();
        Project.setId(ProjectId);

        final Optional<Tasks> Tasks = tasksRepository.findById(id);
        Tasks.ifPresent(value -> value.setProject(Project));

        final TasksDto tasksDto = TasksDto.fromEntity(Tasks.get());
        ProjectDto projectDto = ProjectDto.fromEntity(Project);
        tasksDto.setProject(projectDto);

        return Optional.of(tasksDto).
                orElseThrow(() -> new EntityNotFoundException("No Tasks found with ID = " + id, ErrorCodes.USER_NOT_FOUND));
    }

    @Override
    public List<TasksDto> findByProject(Integer ProjectId) {
        return tasksRepository.findTasksByProjectId(ProjectId).stream()
                .map(TasksDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Tasks id is null");
            return;
        }
        tasksRepository.deleteById(id);
    }
    

}
