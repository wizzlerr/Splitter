CREATE TABLE user_roles (
  id SERIAL PRIMARY KEY  ,
  user_id INT NOT NULL,
  role varchar(45) NOT NULL,
  CONSTRAINT fk_username FOREIGN KEY (user_id) REFERENCES users (id),
  CONSTRAINT u_key UNIQUE (user_id, role));