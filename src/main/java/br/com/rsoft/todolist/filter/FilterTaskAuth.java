package br.com.rsoft.todolist.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.rsoft.todolist.user.User;
import br.com.rsoft.todolist.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {
    private final UserRepository userRepository;

    public FilterTaskAuth(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().startsWith("/tasks")) {
            String authorization = request.getHeader("Authorization");

            String[] usernameAndPassword = extractUsernameAndPassword(authorization);
            String username = usernameAndPassword[0];
            String password = usernameAndPassword[1];

            Optional<User> user = retrieveUser(username, password);
            if (user.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            boolean isValidCredentials = isValidCredentials(password, user.get().getPassword());
            if (!isValidCredentials) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            filterChain.doFilter(request, response);
        }

        filterChain.doFilter(request, response);
    }

    private String[] extractUsernameAndPassword(String authorization) {
        String userPassword = authorization.substring("Basic ".length()).trim();
        byte[] authDecode = Base64.getDecoder().decode(userPassword);
        String userPasswordDecode = new String(authDecode);
        return userPasswordDecode.split(":");
    }

    private Optional<User> retrieveUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    private boolean isValidCredentials(String passwordFromRequest, String passwordFromUser) {
        BCrypt.Result passwordVerifyResult = BCrypt.verifyer().verify(passwordFromRequest.toCharArray(), passwordFromUser.toCharArray());
        return passwordVerifyResult.verified;
    }

}
