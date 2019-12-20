-- User tables --
CREATE TABLE IF NOT EXISTS users (
  id           BIGSERIAL PRIMARY KEY,
  email        VARCHAR(45) NOT NULL UNIQUE,
  password     VARCHAR(100) NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  last_update  TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE IF NOT EXISTS user_roles (
  user_id      BIGINT NOT NULL,
  role_name    VARCHAR(15) NOT NULL,
  CONSTRAINT user_role_fk FOREIGN KEY (user_id) REFERENCES users(id)
);