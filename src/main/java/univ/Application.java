package univ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Component;
import univ.repos.CourseRepo;
import univ.repos.StudentRepo;
import univ.repos.TeacherRepo;

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

        }
    }


}
