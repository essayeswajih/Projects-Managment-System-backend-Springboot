package com.islem.tasks.validators;

import com.islem.tasks.dto.ProjectDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ProjectValidator {
    public static List<String> validatePoject(ProjectDto projectDto) {
        List<String> errors = new ArrayList<>();
        if (projectDto == null) {
            errors.add("Please fill the name");
            errors.add("Please fill the description");
            return errors;
        }
        if (StringUtils.isEmpty(projectDto.getName())) {
            errors.add("Please fill the name");
        }
        if (StringUtils.isEmpty(projectDto.getDescription())) {
            errors.add("Please fill the description");
        }
        return errors;
    }
}
