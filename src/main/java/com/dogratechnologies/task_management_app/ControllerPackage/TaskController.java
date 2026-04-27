package com.dogratechnologies.task_management_app.ControllerPackage;

import com.dogratechnologies.task_management_app.EntityPackage.Task;
import com.dogratechnologies.task_management_app.Service.TaskService;
import com.dogratechnologies.task_management_app.TaskDTO.TaskRequestDTO;
import com.dogratechnologies.task_management_app.TaskDTO.TaskResponseDTO;
import com.dogratechnologies.task_management_app.TaskStatusPackage.TaskStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody TaskRequestDTO dto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(dto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO>getById(@PathVariable Long id)
    {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size, // Size bada kar diya taaki saare tasks aa jayein
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(required = false) TaskStatus status
    )
    {
        Page<TaskResponseDTO> taskPage = taskService.getAllTasks(page, size, sortBy, status);
        return ResponseEntity.ok(taskPage.getContent());
    }


    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO>update(@PathVariable Long id, @Valid @RequestBody TaskRequestDTO dto)
    {
        return ResponseEntity.ok(taskService.updateTask(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id)
    {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task Deleted Successfully");
    }
}
