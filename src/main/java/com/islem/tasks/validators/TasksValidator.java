package com.islem.tasks.validators;

import com.islem.tasks.dto.TasksDto;

import java.util.ArrayList;
import java.util.List;

public class TasksValidator {
    public static List<String> validateTasks(TasksDto tasksDto) {
        List<String> errors = new ArrayList<>();
        if (tasksDto == null) {
            errors.add("Please fill the title");
            errors.add("Please fill the description");
            errors.add("Please select a Project");
            return errors;
        }
        if (tasksDto.getTitle() == null || tasksDto.getTitle().isBlank()) {
            errors.add("Please fill the title");
        }
        if (tasksDto.getDescription() == null || tasksDto.getDescription().isBlank()) {
            errors.add("Please fill the description");
        }
        if (tasksDto.getProject() == null || tasksDto.getProject().getId() == null) {
            errors.add("Please select a Project");
        }
        return errors;
    }
}
