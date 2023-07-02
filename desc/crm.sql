CREATE database crm_app;
USE crm_app;

CREATE TABLE IF NOT EXISTS roles (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(100),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users (
    id INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    fullname VARCHAR(100) NOT NULL,
    avatar VARCHAR(100),
    role_id INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS status (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS jobs (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    start_date DATE,
    end_date DATE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tasks (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    start_date DATE,
    end_date DATE,
    user_id INT NOT NULL,
    job_id INT NOT NULL,
    status_id INT NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE users ADD FOREIGN KEY (role_id) REFERENCES roles (id)  ON DELETE CASCADE;
ALTER TABLE tasks ADD FOREIGN KEY (user_id) REFERENCES users (id)  ON DELETE CASCADE;
ALTER TABLE tasks ADD FOREIGN KEY (job_id) REFERENCES jobs (id)  ON DELETE CASCADE;
ALTER TABLE tasks ADD FOREIGN KEY (status_id) REFERENCES status (id)  ON DELETE CASCADE;

INSERT INTO roles( name, description ) VALUES ("ROLE_ADMIN", "Quản trị hệ thống");
INSERT INTO roles( name, description ) VALUES ("ROLE_MANAGER", "Quản lý");
INSERT INTO roles( name, description ) VALUES ("ROLE_USER", "Nhân viên");

INSERT into users(email,password,fullname, avatar,role_id) values("nguyenvana@gmail.com","123456","Nguyen Van A","",1);
INSERT into users(email,password,fullname, avatar,role_id) values("tranvanb@gmail.com","123456","Tran Van B","",2);
INSERT into users(email,password,fullname, avatar,role_id) values("nguyenthic@gmail.com","123456","Nguyen Thi C","",3);

INSERT INTO status( name ) VALUES ("Chưa thực hiện");
INSERT INTO status( name ) VALUES ("Đang thực hiện");
INSERT INTO status( name ) VALUES ("Đã hoàn thành");

INSERT INTO jobs(name,start_date,end_date) VALUES ("CRM",'2023-05-08','2023-06-08');
INSERT INTO jobs(name,start_date,end_date) VALUES ("JAVA Servlet",'2023-06-01','2023-07-01');
INSERT INTO jobs(name,start_date,end_date) VALUES ("JAVA Spring",'2023-06-20','2023-07-20');

INSERT into tasks(name,start_date,end_date,user_id,job_id,status_id) Values ("Phan tich du an",'2023-01-01','2023-05-05',1,1,3);
INSERT into tasks(name,start_date,end_date,user_id,job_id,status_id) Values ("Thiet ke Database",'2023-02-02','2023-05-05',2,1,3);
INSERT into tasks(name,start_date,end_date,user_id,job_id,status_id) Values ("Front End",'2023-08-08','2023-09-09',3,1,1);

INSERT into tasks(name,start_date,end_date,user_id,job_id,status_id) Values ("Phan tich du an",'2023-02-02','2023-08-08',2,2,3);
INSERT into tasks(name,start_date,end_date,user_id,job_id,status_id) Values ("Thiet ke Database",'2023-02-02','2023-08-08',1,2,2);
INSERT into tasks(name,start_date,end_date,user_id,job_id,status_id) Values ("Front End",'2023-02-02','2023-08-08',3,2,1);

INSERT into tasks(name,start_date,end_date,user_id,job_id,status_id) Values ("Phan tich du an",'2023-09-01','2023-11-02',3,3,3);
INSERT into tasks(name,start_date,end_date,user_id,job_id,status_id) Values ("Thiet ke database",'2023-09-03','2023-07-04',1,3,2);
INSERT into tasks(name,start_date,end_date,user_id,job_id,status_id) Values ("Front End",'2023-09-01','2023-09-09',2,3,2);

SELECT * FROM users;
SELECT * FROM roles;
SELECT * FROM jobs;
SELECT * FROM tasks;
SELECT * FROM status;





