/*
   Add company table where stores all information about company entity
   and create sequence for company table as well as child table for
   company lists.
*/
CREATE TABLE IF NOT EXISTS company (
  id           BIGSERIAL    NOT NULL PRIMARY KEY,
  name         VARCHAR(45)  NOT NULL UNIQUE,
  logo_url     VARCHAR(255),
  headline     VARCHAR(60),
  industry     VARCHAR(45),
  size         VARCHAR(60),
  address      VARCHAR(100),
  about        VARCHAR(255),
  media_url    VARCHAR(255),
  created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  last_update  TIMESTAMP WITHOUT TIME ZONE
);

-- For company benefits
CREATE TABLE IF NOT EXISTS company_benefits (
  company_id  BIGINT       NOT NULL,
  name        VARCHAR(45)  NOT NULL,
  CONSTRAINT company_benefits_fk
      FOREIGN KEY (company_id) REFERENCES company (id)
);

-- For company photos
CREATE TABLE IF NOT EXISTS company_photos (
  company_id  BIGINT       NOT NULL,
  photo_url   VARCHAR(255) NOT NULL,
  CONSTRAINT company_photos_fk
      FOREIGN KEY (company_id) REFERENCES company (id)
);

-- For company links
CREATE TABLE IF NOT EXISTS company_links (
  company_id  BIGINT       NOT NULL,
  url         VARCHAR(255) NOT NULL,
  CONSTRAINT company_links_fk
      FOREIGN KEY (company_id) REFERENCES company (id)
);

-- Add child table where can store company perks
CREATE TABLE IF NOT EXISTS company_perks (
  company_id  BIGINT      NOT NULL,
  name        VARCHAR(45) NOT NULL,
  CONSTRAINT company_perks_fk
      FOREIGN KEY (company_id) REFERENCES company (id)
);

-- Add table location for storing predefined locations
CREATE TABLE IF NOT EXISTS location (
  id            BIGSERIAL   NOT NULL PRIMARY KEY,
  city          VARCHAR(45) NOT NULL,
  country       VARCHAR(45) NOT NULL,
  created_date  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  last_update   TIMESTAMP WITHOUT TIME ZONE
);

-- Add table skill for storing tech stack of skills
CREATE TABLE IF NOT EXISTS skill (
  id            BIGSERIAL   NOT NULL PRIMARY KEY,
  title         VARCHAR(60) NOT NULL,
  created_date  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  last_update   TIMESTAMP WITHOUT TIME ZONE
);

-- Join table for association many-to-many between companies and skills
CREATE TABLE IF NOT EXISTS company_skill (
  company_id BIGINT NOT NULL,
  skill_id   BIGINT NOT NULL,
  -- create surrogate primary key both columns
  CONSTRAINT company_skill_pk
      PRIMARY KEY (company_id, skill_id),
  CONSTRAINT skill_id_fk
      FOREIGN KEY (skill_id)   REFERENCES skill (id),
  CONSTRAINT company_id_fk
      FOREIGN KEY (company_id) REFERENCES company (id)
);

/*
    Add employer table  for storing all employer information
 */
CREATE TABLE IF NOT EXISTS employer (
  id           BIGSERIAL    NOT NULL PRIMARY KEY,
  full_name    VARCHAR(60)  NOT NULL,
  email        VARCHAR(45)  NOT NULL UNIQUE,
  password     VARCHAR(100) NOT NULL,
  company_id   BIGINT       NOT NULL,
  location_id  BIGINT       NOT NULL,
  phone        VARCHAR(18),
  photo_url    VARCHAR(255),
  job_title    VARCHAR(45),
  created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  last_update  TIMESTAMP WITHOUT TIME ZONE,
  CONSTRAINT company_employer_fk
      FOREIGN KEY (company_id)  REFERENCES company (id),
  CONSTRAINT location_employer_fk
      FOREIGN KEY (location_id) REFERENCES location (id)
);

-- Add table job for storing employer open jobs
CREATE TABLE IF NOT EXISTS job (
  id               BIGSERIAL   NOT NULL PRIMARY KEY,
  title            VARCHAR(60) NOT NULL,
  job_role         VARCHAR(60) NOT NULL,
  employer_id      BIGINT      NOT NULL,
  job_experience   VARCHAR(45) NOT NULL,
  employment_type  VARCHAR(60),
  location_id      BIGINT,  -- location is not required because job can be remote
  remote           BOOLEAN,
  relocation       BOOLEAN,
  visa_sponsorship BOOLEAN,
  created_date     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  last_update      TIMESTAMP WITHOUT TIME ZONE,
  CONSTRAINT location_job_fk
      FOREIGN KEY (location_id) REFERENCES location (id),
  CONSTRAINT employer_job_fk
      FOREIGN KEY (employer_id) REFERENCES employer (id)
);

-- Add join table for association many-to-many between jobs and skills
CREATE TABLE IF NOT EXISTS job_skill (
  job_id    BIGINT NOT NULL,
  skill_id  BIGINT NOT NULL,
  CONSTRAINT job_skill_pk
      PRIMARY KEY (job_id, skill_id),
  CONSTRAINT skill_id_fk
      FOREIGN KEY (skill_id) REFERENCES skill (id),
  CONSTRAINT job_id_fk
      FOREIGN KEY (job_id)   REFERENCES job (id)
);

-- Add table talent for storing talent info
CREATE TABLE IF NOT EXISTS talent (
  id           BIGSERIAL    NOT NULL PRIMARY KEY,
  full_name    VARCHAR(60)  NOT NULL,
  email        VARCHAR(45)  NOT NULL UNIQUE,
  password     VARCHAR(100) NOT NULL,
  location_id  BIGINT       NOT NULL,
  photo_url    VARCHAR(255),
  created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  last_update  TIMESTAMP WITHOUT TIME ZONE,
  CONSTRAINT location_talent_fk
      FOREIGN KEY (location_id) REFERENCES location (id)
);

-- Add talent profile table for storing basic information
CREATE TABLE IF NOT EXISTS profile (
  id           BIGINT NOT NULL PRIMARY KEY,
  job_role     VARCHAR(45),
  currency     VARCHAR(30),
  amount       NUMERIC(7, 2),
  status       VARCHAR(12) NOT NULL,
  CONSTRAINT talent_profile_fk
      FOREIGN KEY (id) REFERENCES talent (id)
);

-- Add child table for storing talent profile educations
CREATE TABLE IF NOT EXISTS profile_educations (
  profile_id     BIGINT NOT NULL,
  institution    VARCHAR(60),
  degree         VARCHAR(30),
  specialization VARCHAR(60),
  start_date     date,
  finish_date    date,
  CONSTRAINT profile_education_fk
      FOREIGN KEY (profile_id) REFERENCES profile (id)
);

-- Add child table for storing talent profile expectations
CREATE TABLE IF NOT EXISTS profile_expectations (
  profile_id    BIGINT NOT NULL,
  title         VARCHAR(60),
  CONSTRAINT profile_expectation_fk
      FOREIGN KEY (profile_id) REFERENCES profile (id)
);

-- Add child table for storing talent profile experiences
CREATE TABLE IF NOT EXISTS profile_experiences (
  profile_id    BIGINT       NOT NULL,
  company_name  VARCHAR(60)  NOT NULL,
  position      VARCHAR(45)  NOT NULL,
  description   VARCHAR(255),
  working_now   BOOLEAN,
  start_date    date,
  finish_date   date,
  CONSTRAINT profile_experience_fk
      FOREIGN KEY (profile_id) REFERENCES profile (id)
);

-- Add child table for storing talent selected languages
CREATE TABLE IF NOT EXISTS talent_languages (
  profile_id    BIGINT      NOT NULL,
  name          VARCHAR(60) NOT NULL,
  level         VARCHAR(45) NOT NULL,
  CONSTRAINT talent_language_fk
      FOREIGN KEY (profile_id) REFERENCES profile (id)
);

-- Add child table for storing talent selected preferences
CREATE TABLE IF NOT EXISTS talent_preferences (
  profile_id    BIGINT NOT NULL,
  company_type  VARCHAR (60) NOT NULL,
  CONSTRAINT talent_preference_fk
      FOREIGN KEY (profile_id) REFERENCES profile (id)
);
