create sequence hibernate_sequence start 1 increment 1;

create table company (
  id int8 not null,
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
  company_id int8 not null,
  item varchar(255)
);

create table company_gallery (
  company_id int8 not null,
  photo_url varchar(255)
);

create table company_links (
  company_id int8 not null,
  reference varchar(255)
);

create table company_perks (
  company_id int8 not null,
  item varchar(255)
);

create table company_skill (
  company_id int8 not null,
  skill_id int8 not null,
  primary key (company_id, skill_id)
);

create table employer (
  id int8 not null,
  created_date timestamp not null,
  last_update timestamp,
  full_name varchar(60),
  job_title varchar(30),
  password varchar(100),
  phone varchar(18),
  photo_url varchar(255),
  email varchar(60),
  company_id int8,
  location_id int8 not null,
  primary key (id)
);

create table job (
  id int8 not null,
  created_date timestamp not null,
  last_update timestamp,
  employment_type varchar(255),
  relocation boolean,
  remote boolean,
  visa_sponsorship boolean,
  job_role varchar(255),
  title varchar(60),
  xp_range varchar(255),
  location_id int8,
  employer_id int8 not null,
  primary key (id)
);

create table job_skill (
  job_id int8 not null,
  skill_id int8 not null,
  primary key (job_id, skill_id)
);

create table location (
  id int8 not null,
  created_date timestamp not null,
  last_update timestamp,
  city varchar(30),
  country varchar(30),
  primary key (id)
);

create table profile (
  id int8 not null,
  photo_url varchar(255),
  currency varchar(255),
  value numeric(7, 2),
  job_role varchar(255),
  status varchar(12),
  location_id int8,
  primary key (id)
);

create table profile_educations (
  profile_id int8 not null,
  degree varchar(255),
  institution varchar(255),
  finish_date date,
  start_date date,
  specialization varchar(255)
);

create table profile_expectations (
  profile_id int8 not null,
  expectations varchar(255)
);

create table profile_experiences (
  profile_id int8 not null,
  company varchar(255),
  description varchar(255),
  worked boolean,
  finish_date date,
  start_date date,
  position varchar(255)
);

create table skill (
  id int8 not null,
  created_date timestamp not null,
  last_update timestamp,
  name varchar(30),
  primary key (id)
);

create table talent (
  id int8 not null,
  created_date timestamp not null,
  last_update timestamp,
  email varchar(60),
  full_name varchar(60),
  password varchar(100),
  location_id int8,
  primary key (id)
);

create table talent_languages (
  profile_id int8 not null,
  level varchar(255),
  name varchar(255)
);

create table talent_preferences (
  profile_id int8 not null,
  preferred_company_type varchar(255)
);

alter table company add constraint name_unique unique (name);

alter table employer add constraint email_unique unique (email);

alter table talent add constraint email unique (email);

alter table company_benefits add constraint benefit_id_fk foreign key (company_id) references company;

alter table company_gallery add constraint gallery_id_fk foreign key (company_id) references company;

alter table company_links add constraint link_id_fk foreign key (company_id) references company;

alter table company_perks add constraint perks_id_fk foreign key (company_id) references company;

alter table company_skill add constraint skill_id_fk foreign key (skill_id) references skill;

alter table company_skill add constraint company_id_fk foreign key (company_id) references company;

alter table employer add constraint company_id_fk foreign key (company_id) references company;

alter table employer add constraint location_id_fk foreign key (location_id) references location;

alter table job add constraint location_id_fk foreign key (location_id) references location;

alter table job add constraint employer_id_fk foreign key (employer_id) references employer;

alter table job_skill add constraint skill_id_fk foreign key (skill_id) references skill;

alter table job_skill add constraint job_id_fk foreign key (job_id) references job;

alter table profile add constraint location_id_fk foreign key (location_id) references location;

alter table profile add constraint profile_id_fk foreign key (id) references talent;

alter table profile_educations add constraint education_id_fk foreign key (profile_id) references profile;

alter table profile_expectations add constraint expectation_id_fk foreign key (profile_id) references profile;

alter table profile_experiences add constraint experience_id_fk foreign key (profile_id) references profile;

alter table talent add constraint location_id_fk foreign key (location_id) references location;

alter table talent_languages add constraint language_id_fk foreign key (profile_id) references profile;

alter table talent_preferences add constraint preference_id_fk foreign key (profile_id) references profile;