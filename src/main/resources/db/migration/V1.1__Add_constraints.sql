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