create table teachers (
    teacher_id bigint not null auto_increment,
    department varchar(50),
    teacher_name varchar(50),
    version integer,
    primary key (teacher_id)
);