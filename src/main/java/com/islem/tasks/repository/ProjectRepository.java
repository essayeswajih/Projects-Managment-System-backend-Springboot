package com.islem.tasks.repository;

import com.islem.tasks.dto.ProjectDto;
import com.islem.tasks.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    List<Project> findProjectByUserId(Integer userId);

    @Query("select t.project.id from Tasks t where t.id = :tasksId")
    Integer findProjectByTasksId(Integer tasksId);

    @Query("select c from Project c inner join Tasks t on t.project.id = c.id where t.startDate >= :startDate and t.startDate <= :endDate and c.user.id = :userId")
    List<Project> getAllTasksByProjectsForToday(@Param("startDate") ZonedDateTime startDate, @Param("endDate") ZonedDateTime endDate, @Param("userId") Integer userId);



}
