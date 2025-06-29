INSERT INTO filter (name)
VALUES ('Filter 1'),
       ('Filter 2'),
       ('Filter 3'),
       ('Filter 4'),
       ('Filter 5'),
       ('Filter 6'),
       ('Filter 7'),
       ('Filter 8');

INSERT INTO filter_criteria (filter_id, field, operator, "value")
VALUES (1, 'TITLE', 'CONTAINS', 'USA'),
       (1, 'AMOUNT', 'LESS_THAN_OR_EQUAL', '18'),
       (1, 'DATE', 'LESS_THAN', '2001-09-11');

INSERT INTO filter_criteria (filter_id, field, operator, "value")
VALUES (2, 'AMOUNT', 'GREATER_THAN', '1000'),
       (2, 'TITLE', 'EQUAL', 'EUR'),
       (2, 'DATE', 'GREATER_THAN_OR_EQUAL', '2025-01-01');

INSERT INTO filter_criteria (filter_id, field, operator, "value")
VALUES
    (3, 'TITLE', 'STARTS_WITH', 'A'),
    (3, 'AMOUNT', 'GREATER_THAN', '500'),
    (3, 'DATE', 'LESS_THAN_OR_EQUAL', '2023-12-31');

INSERT INTO filter_criteria (filter_id, field, operator, "value")
VALUES (4, 'TITLE', 'NOT_EQUAL', 'USD'),
       (4, 'AMOUNT', 'NOT_EQUAL', '0'),
       (4, 'DATE', 'GREATER_THAN', '1990-01-01');

INSERT INTO filter_criteria (filter_id, field, operator, "value")
VALUES (5, 'TITLE', 'ENDS_WITH', 'Inc'),
       (5, 'AMOUNT', 'LESS_THAN', '100'),
       (5, 'DATE', 'GREATER_THAN_OR_EQUAL', '2020-06-01');

INSERT INTO filter_criteria (filter_id, field, operator, "value")
VALUES (6, 'AMOUNT', 'EQUAL', '100'),
       (6, 'DATE', 'EQUAL', '2025-06-29');

INSERT INTO filter_criteria (filter_id, field, operator, "value")
VALUES
    (7, 'TITLE', 'CONTAINS', 'Tech'),
    (7, 'TITLE', 'STARTS_WITH', 'X'),
    (7, 'AMOUNT', 'GREATER_THAN_OR_EQUAL', '2500'),
    (7, 'AMOUNT', 'LESS_THAN', '10000'),
    (7, 'DATE', 'GREATER_THAN', '2019-01-01');

INSERT INTO filter_criteria (filter_id, field, operator, "value")
VALUES
    (8, 'TITLE', 'NOT_EQUAL', 'ABC Corp'),
    (8, 'AMOUNT', 'NOT_EQUAL', '0'),
    (8, 'AMOUNT', 'LESS_THAN_OR_EQUAL', '5000'),
    (8, 'DATE', 'GREATER_THAN_OR_EQUAL', '2010-05-15'),
    (8, 'DATE', 'LESS_THAN', '2022-08-30');
