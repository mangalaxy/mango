CREATE TABLE IF NOT EXISTS question (
  id BIGSERIAL PRIMARY KEY,
  message VARCHAR(255) NOT NULL,
  employer_id BIGINT NOT NULL,
  talent_id BIGINT NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  last_update TIMESTAMP WITHOUT TIME ZONE,
  CONSTRAINT talent_question_fk FOREIGN KEY (talent_id) REFERENCES talent (id),
  CONSTRAINT employer_question_fk FOREIGN KEY (employer_id) REFERENCES employer (id)
);

CREATE TABLE IF NOT EXISTS answer (
  id BIGSERIAL PRIMARY KEY,
  message VARCHAR(255) NOT NULL,
  question_id BIGINT NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  last_update TIMESTAMP WITHOUT TIME ZONE,
  CONSTRAINT answer_question_fk FOREIGN KEY (question_id) REFERENCES question (id)
);