package com.dogratechnologies.task_management_app.Service;

import com.dogratechnologies.task_management_app.TaskDTO.TaskRequestDTO;
import com.dogratechnologies.task_management_app.TaskDTO.TaskResponseDTO;
import com.dogratechnologies.task_management_app.TaskStatusPackage.TaskStatus;
import org.springframework.data.domain.Page;

public interface TaskService {
    TaskResponseDTO createTask(TaskRequestDTO dto);
    TaskResponseDTO getTaskById(Long id);
    Page<TaskResponseDTO>getAllTasks(int page , int size, String sortBy, TaskStatus status);
    TaskResponseDTO updateTask(Long id, TaskRequestDTO dto);
    void deleteTask(Long id);

}
