package Service;

import TaskDTO.TaskRequestDTO;
import TaskDTO.TaskResponseDTO;
import TaskStatusPackage.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

public interface TaskService {
    TaskRequestDTO createTask(TaskRequestDTO dto);
    TaskRequestDTO getTaskById(Long id);
    Page<TaskResponseDTO>getAllTasks(int page , int size, String sortBy, TaskStatus status);
    TaskResponseDTO updatedTask(Long id, TaskRequestDTO dto);
    void deleteTask(Long id);

}
