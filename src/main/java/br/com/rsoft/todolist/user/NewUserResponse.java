package br.com.rsoft.todolist.user;

import java.time.LocalDateTime;

public record NewUserResponse(Long id, String username, String name, String password,LocalDateTime createdAt) {
    public NewUserResponse(User user) {
        this(user.getId(), user.getUsername(), user.getName(), user.getPassword(), user.getCreatedAt());
    }
}
