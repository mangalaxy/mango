create sequence hibernate_sequence start 1 increment 1;

create table company (
  id bigserial not null,
  created_date timestamp not null,
  last_update timestamp,
  about varchar(255),
  address varchar(255),
  headline varchar(255),
  industry varchar(60),
  logo_url varchar(255),
  media_url varchar(255),
  name varchar(60),
  size varchar(255),
  primary key (id)
);

create table company_benefits (
  company_id bigserial not null,
  item varchar(255)
);

create table company_gallery (
  company_id bigserial not null,
  photo_url varchar(255)
);

create table company_links (
  company_id bigserial not null,
  reference varchar(255)
);

create table company_perks (
  company_id bigserial not null,
  item varchar(255)
);

create table company_skill (
  company_id bigserial not null,
  skill_id bigserial not null,
  primary key (company_id, skill_id)
);

create table employer (
  id bigserial not null,
  created_date timestamp not null,
  last_update timestamp,
  full_name varchar(60),
  job_title varchar(30),
  password varchar(100),
  phone varchar(18),
  photo_url varchar(255),
  email varchar(60),
  company_id bigserial,
  location_id bigserial not null,
  primary key (id)
);

create table job (
  id bigserial not null,
  created_date timestamp not null,
  last_update timestamp,
  employment_type varchar(255),
  relocation boolean,
  remote boolean,
  visa_sponsorship boolean,
  job_role varchar(255),
  title varchar(60),
  xp_range varchar(255),
  location_id bigserial,
  employer_id bigserial not null,
  primary key (id)
);

create table job_skill (
  job_id bigserial not null,
  skill_id bigserial not null,
  primary key (job_id, skill_id)
);

create table location (
  id bigserial not null,
  created_date timestamp not null,
  last_update timestamp,
  city varchar(30),
  country varchar(30),
  primary key (id)
);

create table profile (
  id bigserial not null,
  photo_url varchar(255),
  currency varchar(255),
  value numeric(7, 2),
  job_role varchar(255),
  status varchar(12),
  location_id bigserial,
  primary key (id)
);

create table profile_educations (
  profile_id bigserial not null,
  degree varchar(255),
  institution varchar(255),
  finish_date date,
  start_date date,
  specialization varchar(255)
);

create table profile_expectations (
  profile_id bigserial not null,
  expectations varchar(255)
);

create table profile_experiences (
  profile_id bigserial not null,
  company varchar(255),
  description varchar(255),
  worked boolean,
  finish_date date,
  start_date date,
  position varchar(255)
);

create table skill (
  id bigserial not null,
  created_date timestamp not null,
  last_update timestamp,
  name varchar(30),
  primary key (id)
);

create table talent (
  id bigserial not null,
  created_date timestamp not null,
  last_update timestamp,
  email varchar(60),
  full_name varchar(60),
  password varchar(100),
  location_id bigserial,
  primary key (id)
);

create table talent_languages (
  profile_id bigserial not null,
  level varchar(255),
  name varchar(255)
);

create table talent_preferences (
  profile_id bigserial not null,
  preferred_company_type varchar(255)
);