use mysql;
create database `auth-test` character set utf8mb4 collate utf8mb4_bin;
use `auth-test`;

set names utf8mb4;
set foreign_key_checks=0;

create table `user`(
    id varchar(255) not null primary key,
    name varchar(255),
    password varchar(255)
);

create table `user_role`(
    id int not null auto_increment primary key,
    user_id varchar(255),
    role_id varchar(255)
);

create table `role`(
    id varchar(255) not null primary key,
    name varchar(255)
);
