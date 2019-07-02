INSERT INTO location
  (id, city, country)
VALUES
  (1, 'Kyiv', 'Ukraine');

INSERT INTO employer
  (id, full_name, email, password, phone, job_title, location_id)
VALUES
  (1, 'Ivan Ivanov', 'first.user@test.com', '$2a$10$LsVsLTHNDaJDu8dDbkGEk.4qDE8zIuiqvQ1Kvo99ET.gd.rqUQZjW', '050-450-22-33', 'DanIt', 1);

INSERT INTO employer_roles
  (employer_id, role_id)
VALUES
  (1, 0);