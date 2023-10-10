package br.com.rsoft.todolist.user;

import java.time.LocalDateTime;

public record NewUserResponse(Long id, String username, String name, LocalDateTime createdAt) {
    public NewUserResponse(User user) {
        this(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt());
    }
}
