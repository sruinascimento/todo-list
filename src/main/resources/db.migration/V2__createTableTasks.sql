CREATE TABLE tasks
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255),
    priority    VARCHAR(15) NOT NULL,
    status      VARCHAR(15) NOT NULL DEFAULT 'OPEN',
    created_at  DATETIME             DEFAULT CURRENT_TIMESTAMP,
    start_at    DATETIME,
    end_at      DATETIME,
    user_id     BIGINT,
    CONSTRAINT `fk_users_tasks`
        FOREIGN KEY (user_id) REFERENCES users (id)
);