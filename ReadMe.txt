1. Created database Register
2. Create table student

MySQL:
localHost:
userName:
password:
String url = "jdbc:mysql://localhost:3306/REGISTER";
        String userName = "root";
        String password = "root";


Queries in SQL:

SHOW DATABASES;

CREATE DATABASE REGISTER;

USE REGISTER;

SHOW TABLES;

create table student (
roll_number int(2),
nameOfStudent varchar(10)
);
select * from student;

insert into student values (9, 'Mani');

select nameOfStudent from student where roll_number = 2;
