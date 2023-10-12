package br.com.rsoft.todolist.task;

import br.com.rsoft.todolist.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

import static br.com.rsoft.todolist.task.Status.OPEN;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(length = 50)
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @Enumerated(EnumType.STRING)
    private Status status = OPEN;
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(name = "start_at")
    private LocalDateTime startAt;
    @Column(name = "end_at")
    private LocalDateTime endAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Deprecated
    public Task() {
    }

    public Task(String title,
                String description,
                Priority priority,
                Status status,
                LocalDateTime startAt,
                LocalDateTime endAt,
                User user) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.startAt = startAt;
        this.endAt = endAt;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public User getUser() {
        return user;
    }
}
