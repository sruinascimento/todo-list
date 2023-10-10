package br.com.rsoft.todolist.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NewUserRequest(
        @NotBlank
        @Size(min = 5, max = 30)
        String username,
        @NotBlank
        @Size(min = 5, max = 100)
        String name,
        @NotBlank
        @Size(min = 8, max = 255)
        String password) {
    public User toModel() {
        return new User(username, name, password);
    }
}
