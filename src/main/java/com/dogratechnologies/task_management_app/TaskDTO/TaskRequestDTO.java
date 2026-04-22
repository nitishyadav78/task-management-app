package com.dogratechnologies.task_management_app.TaskDTO;

import com.dogratechnologies.task_management_app.TaskStatusPackage.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDTO {
    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    private TaskStatus status;
}
