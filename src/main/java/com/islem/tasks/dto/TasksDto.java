package com.islem.tasks.dto;
import com.islem.tasks.entity.Project;
import com.islem.tasks.entity.Tasks;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.ZonedDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TasksDto {

    private Integer id;

    private String title;

    private String description;

    private ZonedDateTime startDate;

    private boolean done;

    private boolean favorite;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ProjectDto project;

    public static Tasks toEntity (TasksDto tasksDto) {
        final Tasks tasks = new Tasks();
        tasks.setId(tasksDto.getId());
        tasks.setTitle(tasksDto.getTitle());
        tasks.setDescription(tasksDto.getDescription());
        tasks.setDone(tasksDto.isDone());
        tasks.setFavorite(tasksDto.isFavorite());
        tasks.setStartDate(ZonedDateTime.now());
        tasks.setProject(ProjectDto.toEntity(tasksDto.getProject()));

        return tasks;
    }
    public static TasksDto fromEntity(Tasks tasks ) {
        return TasksDto.builder()
                .id(tasks.getId())
                .title(tasks.getTitle())
                .description(tasks.getDescription())
                .startDate(tasks.getStartDate())
                .done(tasks.isDone())
                .favorite(tasks.isFavorite())
                .build();
    }
}