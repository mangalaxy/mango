CREATE TABLE IF NOT EXISTS company (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(45) NOT NULL UNIQUE,
  headline VARCHAR(255),
  logo_url VARCHAR(255),
  industry VARCHAR(100),
  size VARCHAR(45),
  headquarters_address VARCHAR(255),
  about TEXT,
  promo_url VARCHAR(255),
  created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  last_update TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE IF NOT EXISTS company_benefits (
  company_id BIGINT NOT NULL,
  name VARCHAR(60) NOT NULL,
  CONSTRAINT company_benefit_fk FOREIGN KEY (company_id) REFERENCES company (id)
);

CREATE TABLE IF NOT EXISTS company_perks (
  company_id BIGINT NOT NULL,
  name VARCHAR(60) NOT NULL,
  CONSTRAINT company_perk_fk FOREIGN KEY (company_id) REFERENCES company (id)
);

CREATE TABLE IF NOT EXISTS company_photos (
  company_id BIGINT NOT NULL,
  photo_url VARCHAR(255) NOT NULL,
  CONSTRAINT company_photo_fk FOREIGN KEY (company_id) REFERENCES company (id)
);

CREATE TABLE IF NOT EXISTS company_links (
  company_id BIGINT NOT NULL,
  url VARCHAR(255) NOT NULL,
  CONSTRAINT company_link_fk FOREIGN KEY (company_id) REFERENCES company (id)
);

CREATE TABLE IF NOT EXISTS location (
  id SMALLSERIAL PRIMARY KEY,
  city VARCHAR(45) NOT NULL,
  country VARCHAR(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS skill (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(60) NOT NULL UNIQUE,
  created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  last_update TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE IF NOT EXISTS company_skill (
  company_id BIGINT NOT NULL,
  skill_id BIGINT NOT NULL,
  CONSTRAINT company_skill_pk PRIMARY KEY (company_id, skill_id),
  CONSTRAINT skill_id_fk FOREIGN KEY (skill_id) REFERENCES skill (id),
  CONSTRAINT company_id_fk FOREIGN KEY (company_id) REFERENCES company (id)
);

CREATE TABLE IF NOT EXISTS employer (
  id BIGSERIAL PRIMARY KEY,
  full_name VARCHAR(60) NOT NULL,
  email VARCHAR(45) NOT NULL UNIQUE,
  password VARCHAR(64) NOT NULL,
  location_id SMALLINT NOT NULL,
  company_id BIGINT NOT NULL,
  phone VARCHAR(18),
  photo_url VARCHAR(255),
  job_title VARCHAR(45),
  role VARCHAR(45) NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  last_update TIMESTAMP WITHOUT TIME ZONE,
  CONSTRAINT company_employer_fk FOREIGN KEY (company_id) REFERENCES company (id),
  CONSTRAINT location_employer_fk FOREIGN KEY (location_id) REFERENCES location (id)
);

CREATE TABLE IF NOT EXISTS job_role (
  id SMALLSERIAL PRIMARY KEY,
  title VARCHAR(60) NOT NULL
);

CREATE TABLE IF NOT EXISTS job (
  id BIGSERIAL PRIMARY KEY,
  title VARCHAR(60) NOT NULL,
  job_role_id SMALLINT NOT NULL,
  employer_id BIGINT NOT NULL,
  location_id SMALLINT,
  job_type VARCHAR(60),
  remote BOOLEAN,
  relocation BOOLEAN,
  visa_sponsorship BOOLEAN,
  experience_required VARCHAR(45) NOT NULL,
  description TEXT,
  created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  last_update TIMESTAMP WITHOUT TIME ZONE,
  CONSTRAINT location_job_fk FOREIGN KEY (location_id) REFERENCES location (id),
  CONSTRAINT employer_job_fk FOREIGN KEY (employer_id) REFERENCES employer (id),
  CONSTRAINT job_role_fk FOREIGN KEY (job_role_id) REFERENCES job_role (id)
);

CREATE TABLE IF NOT EXISTS job_skill (
  job_id BIGINT NOT NULL,
  skill_id BIGINT NOT NULL,
  CONSTRAINT job_skill_pk PRIMARY KEY (job_id, skill_id),
  CONSTRAINT skill_id_fk FOREIGN KEY (skill_id) REFERENCES skill (id),
  CONSTRAINT job_id_fk FOREIGN KEY (job_id) REFERENCES job (id)
);

CREATE TABLE IF NOT EXISTS talent (
  id BIGSERIAL PRIMARY KEY,
  full_name VARCHAR(60) NOT NULL,
  email VARCHAR(45) NOT NULL UNIQUE,
  password VARCHAR(64) NOT NULL,
  location_id SMALLINT NOT NULL,
  role VARCHAR(45) NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  last_update TIMESTAMP WITHOUT TIME ZONE,
  CONSTRAINT location_talent_fk FOREIGN KEY (location_id) REFERENCES location (id)
);

CREATE TABLE IF NOT EXISTS profile (
  id BIGINT NOT NULL PRIMARY KEY,
  photo_url VARCHAR(255),
  job_role VARCHAR(60) NOT NULL,
  currency VARCHAR(3),
  amount NUMERIC(9, 2),
  location_id SMALLINT,
  status VARCHAR(12) NOT NULL,
  CONSTRAINT talent_profile_fk FOREIGN KEY (id) REFERENCES talent (id),
  CONSTRAINT location_profile_fk FOREIGN KEY (location_id) REFERENCES location (id)
);

CREATE TABLE IF NOT EXISTS profile_skill (
  profile_id BIGINT NOT NULL,
  skill_id BIGINT NOT NULL,
  CONSTRAINT profile_skill_pk PRIMARY KEY (profile_id, skill_id),
  CONSTRAINT profile_id_fk FOREIGN KEY (profile_id) REFERENCES profile (id),
  CONSTRAINT skill_id_fk FOREIGN KEY (skill_id) REFERENCES skill (id)
);

CREATE TABLE IF NOT EXISTS talent_educations (
  profile_id BIGINT NOT NULL,
  institution VARCHAR(60),
  degree VARCHAR(30),
  specialization VARCHAR(60),
  start_date DATE,
  finish_date DATE,
  CONSTRAINT talent_education_fk FOREIGN KEY (profile_id) REFERENCES profile (id)
);

CREATE TABLE IF NOT EXISTS talent_expectations (
  profile_id BIGINT NOT NULL,
  title VARCHAR(60) NOT NULL,
  CONSTRAINT talent_expectation_fk FOREIGN KEY (profile_id) REFERENCES profile (id)
);

CREATE TABLE IF NOT EXISTS talent_experiences (
  profile_id BIGINT NOT NULL,
  company_name VARCHAR(60) NOT NULL,
  position VARCHAR(45) NOT NULL,
  description VARCHAR(255),
  is_working BOOLEAN,
  start_date DATE,
  finish_date DATE,
  CONSTRAINT talent_experience_fk FOREIGN KEY (profile_id) REFERENCES profile (id)
);

CREATE TABLE IF NOT EXISTS talent_languages (
  profile_id BIGINT NOT NULL,
  name VARCHAR(30) NOT NULL,
  level VARCHAR(15) NOT NULL,
  CONSTRAINT talent_language_fk FOREIGN KEY (profile_id) REFERENCES profile (id)
);

CREATE TABLE IF NOT EXISTS talent_preferences (
  profile_id BIGINT NOT NULL,
  company_type VARCHAR (60) NOT NULL,
  CONSTRAINT talent_preference_fk FOREIGN KEY (profile_id) REFERENCES profile (id)
);

CREATE TABLE IF NOT EXISTS topic (
  id SERIAL PRIMARY KEY,
  title VARCHAR(60) NOT NULL
);

CREATE TABLE IF NOT EXISTS post (
  id SERIAL PRIMARY KEY,
  headline VARCHAR(60) NOT NULL,
  opening VARCHAR(255) NOT NULL,
  author VARCHAR(60) NOT NULL,
  image_url VARCHAR(255) NOT NULL,
  content TEXT NOT NULL,
  views_count INT,
  likes_count INT,
  topic_id INT NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  last_update TIMESTAMP WITHOUT TIME ZONE,
  CONSTRAINT post_topic_fk FOREIGN KEY (topic_id) REFERENCES topic (id)
);

CREATE TABLE IF NOT EXISTS bookmarked_talents (
  employer_id BIGINT NOT NULL,
  talent_id BIGINT NOT NULL,
  CONSTRAINT employer_talent_pk PRIMARY KEY (employer_id, talent_id),
  CONSTRAINT employer_id_fk FOREIGN KEY (employer_id) REFERENCES employer (id),
  CONSTRAINT talent_id_fk FOREIGN KEY (talent_id) REFERENCES talent (id)
);