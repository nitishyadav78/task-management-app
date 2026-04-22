package Service;
import EntityPackage.Task;
import ExceptionPackage.ResourceNotFoundException;
import RepsitoryPackage.TaskRepository;
import TaskDTO.TaskRequestDTO;
import TaskDTO.TaskResponseDTO;
import TaskStatusPackage.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepo;


    @Override
    @CacheEvict(value = "tasks" , allEntries = true)
    public TaskResponseDTO createTask(TaskRequestDTO dto)
    {
        Task task = Task.builder()
                        .title(dto.getTitle())
                        .description(dto.getDescription())
                        .status(dto.getStatus() != null ? dto.getStatus() : TaskStatus.TODO)
                        .build();

        return MapToDTO(taskRepo.save(task));
    }

    @Override
    @CacheEvict(value = "tasks", key = "#id")
    public TaskResponseDTO getTaskById(Long id)
    {
        Task task = taskRepo.findById(id).orElseThrow(
                () ->new ResourceNotFoundException("Task not found with id: " + id));
        return MapToDTO(task);
    }

    @Override
    @Cacheable(value = "tasks" , key = "#page + '_' + #size + '_' + #sortBy + '_' + #status")
    public Page<TaskResponseDTO> getAllTasks(int  page, int size, String sortBy, TaskStatus status)
    {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        Page<Task>tasks = (status != null)
                ?taskRepo.findByStatus(status, pageable)
                : taskRepo.findAll(pageable);

        return tasks.map(this ::MapToDTO);
    }

    @Override
    @CacheEvict(value = "tasks",allEntries = true)
    public  TaskResponseDTO updateTask(Long id,  TaskRequestDTO dto)
    {
        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: "+ id));
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        if(task.getStatus() != null)
        {
            task.setStatus(dto.getStatus());
        }
        return MapToDTO(taskRepo.save(task));
    }

    @Override
    @CacheEvict(value = "tasks", allEntries = true)
    public void deleteTask(Long id)
    {
        if(!taskRepo.existsById(id))
        {
            throw new ResourceNotFoundException("Task not found with id" + id);
        }

        taskRepo.deleteById(id);
    }

    private TaskResponseDTO MapToDTO(Task task)
    {
        return TaskResponseDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .createdAt(task.getCreatedAt())
                .build();
    }


}
