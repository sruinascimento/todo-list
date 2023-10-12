package br.com.rsoft.todolist.task;

import java.time.format.DateTimeFormatter;

public record NewTaskResponse(
        Long id,
        String title,
        String description,
        Priority priority,
        Status status,
        String startAt,
        String endAt,
        String username
) {
    public NewTaskResponse(Task task) {
        this(task.getId(), task.getTitle(), task.getDescription(), task.getPriority(), task.getStatus(),
                task.getStartAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                task.getEndAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                task.getUser().getUsername());
    }


}
