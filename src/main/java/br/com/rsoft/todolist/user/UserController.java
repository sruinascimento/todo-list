package br.com.rsoft.todolist.user;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/users")
    public ResponseEntity<String> create(@RequestBody @Valid NewUserRequest request) {
        User newUser = request.toModel();

       if (repository.existsByUsername(newUser.getUsername())) {
           return ResponseEntity.badRequest().body(String.format("Username %s already exists", newUser.getUsername()));
       }

        repository.save(newUser);

        return ResponseEntity.ok(new NewUserResponse(newUser).toString());
    }

}
