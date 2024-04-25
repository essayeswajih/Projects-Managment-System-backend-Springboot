package com.islem.tasks.repository;

import com.islem.tasks.entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TasksRepository extends JpaRepository<Tasks, Integer>{
    List<Tasks> findTasksByProjectId(Integer projectId);
}
