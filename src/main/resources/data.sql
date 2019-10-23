INSERT INTO location (id, city, country) VALUES (1, 'Kyiv', 'Ukraine');
INSERT INTO location (id, city, country) VALUES (2, 'Lviv', 'Ukraine');

INSERT INTO talent (id, created_date, last_update, full_name, email, password, location_id) VALUES (1, '2019-03-12 14:00:00', '2019-03-13 14:01:00', 'Ivan Ivanov', 'test@gmail.com', '123456', 1);
INSERT INTO talent (id, created_date, last_update, full_name, email, password, location_id) VALUES (2, '2019-03-13 15:00:00', '2019-03-13 15:01:00', 'Petr Sidorov', 'petr@gmail.com', '654321', 2);

INSERT INTO profile (id, photo_url, job_role) VALUES (1, 'http://some-photo.com', 'Java Developer');
INSERT INTO profile (id, photo_url, job_role) VALUES (2, 'http://some-photo2.com', 'JS Developer');

INSERT INTO employer (id, created_date, last_update, full_name, email, password, location_id) VALUES (1, '2019-08-21 14:00:00', '2019-08-21 14:01:00', 'Elon Mask', 'elon@gmail.com', '123456', 1);
INSERT INTO employer (id, created_date, last_update, full_name, email, password, location_id) VALUES (2, '2019-08-21 14:00:00', '2019-08-21 14:01:00', 'Mark Zuckerberg', 'mark@gmail.com', '123456', 2);

INSERT INTO job (id, created_date, last_update, title, location_id, job_role, employer_id) VALUES (1, '2019-08-21 14:00:00', '2019-08-21 14:01:00', 'Java Developer', 1, 'role1', 1);
INSERT INTO job (id, created_date, last_update, title, location_id, job_role, employer_id) VALUES (2, '2019-08-21 14:00:00', '2019-08-21 14:01:00', 'JS Developer', 2, 'role2', 1);
INSERT INTO job (id, created_date, last_update, title, location_id, job_role, employer_id) VALUES (3, '2019-08-21 14:00:00', '2019-08-21 14:01:00', 'Front End', 1, 'role2', 2);

INSERT INTO question (id, created_date, last_update, message, talent_id) VALUES (1, '2019-03-12 14:00:00', '2019-03-13 14:01:00', 'Some question 1', 1);
INSERT INTO question (id, created_date, last_update, message, talent_id) VALUES (2, '2019-03-12 14:00:00', '2019-03-13 14:01:00', 'Some question 2', 1);
