CREATE SCHEMA IF NOT EXISTS wise_task_task;


CREATE TABLE wise_task_task.task
(
    id          UUID    NOT NULL PRIMARY KEY,
    name        VARCHAR NOT NULL,
    description VARCHAR NOT NULL,
    category    VARCHAR NOT NULL,
    author_id   UUID    NOT NULL,
    graph_id    UUID
);

CREATE TABLE wise_task_task.condition
(
    id        UUID NOT NULL PRIMARY KEY,
    task_id   UUID NOT NULL,
    plugin_id UUID NOT NULL,

    CONSTRAINT fk_task_id FOREIGN KEY(task_id) REFERENCES wise_task_task.task(id)
);

CREATE TABLE wise_task_task.property
(
    id       UUID    NOT NULL PRIMARY KEY,
    response BOOLEAN NOT NULL,

    CONSTRAINT fk_condition_id FOREIGN KEY(id) REFERENCES wise_task_task.condition(id)

);

CREATE TABLE wise_task_task.characteristic
(
    id       UUID NOT NULL PRIMARY KEY,
    response INT  NOT NULL,

    CONSTRAINT fk_condition_id FOREIGN KEY(id) REFERENCES wise_task_task.condition(id)
);

CREATE TABLE wise_task_task.solution
(
    id         UUID    NOT NULL PRIMARY KEY,
    task_id    UUID    NOT NULL,
    author_id  UUID    NOT NULL,
    is_correct BOOLEAN NOT NULL,
    graph_id   UUID    NOT NULL,

    CONSTRAINT fk_task_id FOREIGN KEY(task_id) REFERENCES wise_task_task.task(id)
);