INSERT INTO location (id, city, country) VALUES (1, 'Kyiv', 'Ukraine');
INSERT INTO location (id, city, country) VALUES (2, 'Lviv', 'Ukraine');

INSERT INTO company (id, created_date, last_update, name) VALUES (1, '2019-03-12 14:00:00', '2019-03-13 14:01:00', 'NewCompany');

INSERT INTO talent (id, created_date, last_update, full_name, email, password, location_id) VALUES (1, '2019-03-12 14:00:00', '2019-03-13 14:01:00', 'Ivan Ivanov', 'test@gmail.com', '123456', 1);
INSERT INTO talent (id, created_date, last_update, full_name, email, password, location_id) VALUES (2, '2019-03-13 15:00:00', '2019-03-13 15:01:00', 'Petr Sidorov', 'petr@gmail.com', '654321', 2);

INSERT INTO profile (id, photo_url, job_role) VALUES (1, 'http://some-photo.com', 'Java Developer');
INSERT INTO profile (id, photo_url, job_role) VALUES (2, 'http://some-photo2.com', 'JS Developer');

INSERT INTO employer (id, created_date, last_update, full_name, email, password, location_id, company_id) VALUES (1, '2019-08-21 14:00:00', '2019-08-21 14:01:00', 'Elon Mask', 'elon@gmail.com', '123456', 1, 1);
INSERT INTO employer (id, created_date, last_update, full_name, email, password, location_id, company_id) VALUES (2, '2019-08-21 14:00:00', '2019-08-21 14:01:00', 'Mark Zuckerberg', 'mark@gmail.com', '123456', 2, 1);

INSERT INTO job_role (id, title) VALUES (1, 'Role1');
INSERT INTO job_role (id, title) VALUES (2, 'Role2');

INSERT INTO job (id, created_date, last_update, title, location_id, employer_id, job_experience, job_role_id) VALUES (1, '2019-08-21 14:00:00', '2019-08-21 14:01:00', 'Java Developer', 1, 1, '5 years', 1);
INSERT INTO job (id, created_date, last_update, title, location_id, employer_id, job_experience, job_role_id) VALUES (2, '2019-08-21 14:00:00', '2019-08-21 14:01:00', 'JS Developer', 2, 1, '5 years', 2);
INSERT INTO job (id, created_date, last_update, title, location_id, employer_id, job_experience, job_role_id) VALUES (3, '2019-08-21 14:00:00', '2019-08-21 14:01:00', 'Front End', 1, 2, '5 years', 2);

INSERT INTO users (id, created_date, last_update, email, password) VALUES (1, '2019-03-12 14:00:00', '2019-03-13 14:01:00', 'nikolai.blashchuk@gmail.com', '$2a$04$kDaKwBckpCiw/PFvV4qpqOdMl9oypQVKaXvANn.oeKC9xrGiYdfmO');

INSERT INTO user_roles (user_id, role_name) VALUES (1, 0);
INSERT INTO user_roles (user_id, role_name) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_name) VALUES (1, 2);
INSERT INTO user_roles (user_id, role_name) VALUES (1, 3);


