INSERT INTO location (id, city, country) VALUES (1, 'Kyiv', 'Ukraine');
INSERT INTO location (id, city, country) VALUES (2, 'Lviv', 'Ukraine');

INSERT INTO talent (id, created_date, last_update, full_name, email, password, location_id) VALUES (1, '2019-03-12 14:00:00', '2019-03-13 14:01:00', 'Ivan Ivanov', 'test@gmail.com', '123456', 1);
INSERT INTO talent (id, created_date, last_update, full_name, email, password, location_id) VALUES (2, '2019-03-13 15:00:00', '2019-03-13 15:01:00', 'Petr Sidorov', 'petr@gmail.com', '654321', 2);

INSERT INTO profile (id, photo_url, job_role) VALUES (1, 'http://some-photo.com', 'Java Developer');
INSERT INTO profile (id, photo_url, job_role) VALUES (2, 'http://some-photo2.com', 'JS Developer');
