package com.dogratechnologies.task_management_app.RepsitoryPackage;

import com.dogratechnologies.task_management_app.EntityPackage.Task;
import com.dogratechnologies.task_management_app.TaskStatusPackage.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByStatus(TaskStatus status, Pageable pageable);
}
