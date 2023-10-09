package br.com.rsoft.todolist.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/users")
    public void create(@RequestBody NewUserRequest request) {
        System.out.println(request);
    }

}
