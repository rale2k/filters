CREATE TABLE IF NOT EXISTS filter
(
    id   BIGSERIAL
        constraint pk_filter
            primary key,
    name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS filter_criteria
(
    id        BIGSERIAL
        constraint pk_filter_criteria
            primary key,
    filter_id BIGINT NOT NULL,
    field     TEXT   NOT NULL,
    operator  TEXT   NOT NULL,
    "value"     TEXT   NOT NULL,

    CONSTRAINT fk_filter
        FOREIGN KEY (filter_id)
            REFERENCES filter (id)
);
