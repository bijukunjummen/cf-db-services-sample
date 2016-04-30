package univ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Component;
import univ.domain.Course;
import univ.domain.Teacher;
import univ.repos.CourseRepo;
import univ.repos.StudentRepo;
import univ.repos.TeacherRepo;

import java.util.Optional;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder();
        builder.sources(Application.class).run(args);
    }

    @Component
    public static class SampleDataPopulator implements CommandLineRunner {

        @Autowired
        private CourseRepo courseRepo;

        @Autowired
        private StudentRepo studentRepo;

        @Autowired
        private TeacherRepo teacherRepo;

        @Override
        public void run(String... args) throws Exception {
            Teacher teacher1 = sampleTeacher("teacher1", "department1");
            Teacher teacher2 = sampleTeacher("teacher2", "department1");

            teacher1 = saveIfNew(teacher1);
            teacher2 = saveIfNew(teacher2);

            Course course1 = sampleCourse("CRS1", "Course 1");
            course1.setTeacher(teacher1);

            Course course2 = sampleCourse("CRS2", "Course 2");
            course2.setTeacher(teacher2);

            saveIfNew(course1);
            saveIfNew(course2);
        }

        private Teacher saveIfNew(Teacher teacher) {
            Optional<Teacher> fromDB = this.teacherRepo.findByName(teacher.getName());
            if(!fromDB.isPresent()) {
                return this.teacherRepo.save(teacher);
            }
            return fromDB.get();
        }

        private Course saveIfNew(Course course) {
            Optional<Course> fromDb = this.courseRepo.findByCourseCode(course.getCourseCode());

            if (!fromDb.isPresent()) {
                return this.courseRepo.save(course);
            }
            return fromDb.get();
        }
        private Teacher sampleTeacher(String name, String department) {
            return new Teacher(name, department);
        }

        private Course sampleCourse(String courseCode, String courseName) {
            return new Course(courseCode, courseName);
        }
    }


}
