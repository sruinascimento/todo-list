package br.com.rsoft.todolist.task;

import br.com.rsoft.todolist.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskController(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/tasks")
    public ResponseEntity<String> create(@RequestBody @Valid NewTaskRequest newTaskRequest) {
        var user = userRepository.findByUsername(newTaskRequest.username());
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body(String.format("Username %s don\'t exists", newTaskRequest.username()));
        }

        var existsByTitle = taskRepository.existsByTitle(newTaskRequest.title());
        if (existsByTitle) {
            return ResponseEntity.badRequest().body(String.format("Task %s already exists", newTaskRequest.title()));
        }
        var task = newTaskRequest.toModel(user.get());
        taskRepository.save(task);

        return ResponseEntity.ok(new NewTaskResponse(task).toString());
    }
}
