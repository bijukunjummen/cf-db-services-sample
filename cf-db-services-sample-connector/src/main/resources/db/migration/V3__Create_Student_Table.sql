create table students (
    student_id bigint not null auto_increment,
    birth_date datetime not null,
    student_name varchar(50),
    version integer,
    primary key (student_id));

create table student_course (
    student_id bigint not null,
    course_id bigint not null) ;

alter table student_course add constraint student_to_course_fk1 foreign key (course_id) references courses (course_id);

alter table student_course add constraint student_to_course_fk2 foreign key (student_id) references students (student_id);