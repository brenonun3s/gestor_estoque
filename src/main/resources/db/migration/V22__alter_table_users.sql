ALTER TABLE tb_usuarios RENAME COLUMN username TO name;
ALTER TABLE tb_usuarios RENAME COLUMN senha TO password;

ALTER TABLE tb_usuarios DROP COLUMN roles;

ALTER TABLE tb_usuarios ADD COLUMN email VARCHAR(255) NOT NULL;

ALTER TABLE tb_usuarios ADD CONSTRAINT uk_tb_usuarios_email UNIQUE (email);