CREATE TABLE filter
(
    id   BIGINT      NOT NULL,
    name VARCHAR(50) NOT NULL,

    CONSTRAINT pk_filter PRIMARY KEY (id)
);

CREATE TABLE filter_criteria
(
    id        BIGINT NOT NULL,
    filter_id BIGINT NOT NULL,
    field     TEXT   NOT NULL,
    value     TEXT   NOT NULL,
    CONSTRAINT pk_filter_criteria PRIMARY KEY (id),

    CONSTRAINT fk_filter
        FOREIGN KEY (filter_id)
            REFERENCES filter (id)
);
