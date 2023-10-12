package br.com.rsoft.todolist.task;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    boolean existsByTitle(String title);
}
