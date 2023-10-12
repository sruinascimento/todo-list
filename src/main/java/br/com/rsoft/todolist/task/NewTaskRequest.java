package br.com.rsoft.todolist.task;

import br.com.rsoft.todolist.user.User;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record NewTaskRequest(
        @NotBlank
        @Size(min = 3, max = 50)
        String title,
        String description,
        @NotNull
        Priority priority,
        @NotBlank
        @Size(min = 5, max = 30)
        String username,
        @NotNull
        LocalDateTime startAt,
        @FutureOrPresent
        LocalDateTime endAt) {

    public Task toModel(User user) {
       return new Task(title(), description(), priority(), Status.OPEN, startAt(), endAt(), user);
    }
}
