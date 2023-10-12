package br.com.rsoft.todolist.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NewUserRequest(
        @NotBlank
        @Size(min = 5, max = 30)
        String username,
        @NotBlank
        @Size(min = 3, max = 100)
        String name,
        @NotBlank
        @Size(min = 8, max = 255)
        String password) {
    public User toModel() {
        return new User(username, name,  BCrypt.withDefaults().hashToString(12, password.toCharArray()));
    }
}
