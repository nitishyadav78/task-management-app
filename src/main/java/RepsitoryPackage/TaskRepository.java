package RepsitoryPackage;

import EntityPackage.Task;
import EntityPackage.User;
import TaskStatusPackage.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task>findByStatus(TaskStatus status, Pageable pageable);
}
