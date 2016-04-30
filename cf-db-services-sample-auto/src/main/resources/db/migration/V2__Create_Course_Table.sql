create table courses (
    course_id bigint not null auto_increment,
    course_code varchar(10) not null,
    course_name varchar(50),
    version integer,
    teacher_id bigint,
    primary key (course_id));

alter table courses add constraint course_to_teacher_fk foreign key (teacher_id) references teachers (teacher_id);