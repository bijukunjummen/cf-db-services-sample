package noscan.univ;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import univ.domain.Course;
import univ.repos.CourseRepo;
import univ.repos.TeacherRepo;
import univ.util.ClasspathResourceUtils;
import univ.web.CourseController;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class CourseControllerMvcTest {

    @Autowired
    private CourseController courseController;

    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSaveCourse() throws Exception {
        String sampleCourseRequest = ClasspathResourceUtils.getResourceContentFromPath("/samples/sampleCourse.json");
        this.mockMvc.perform(
                post("/courses")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(sampleCourseRequest))
                .andDo(log())
                .andExpect(status().isCreated());

        verify(teacherRepo, times(1)).findOne(3L);
        verify(courseRepo, times(1)).save(any(Course.class));
    }

    @Configuration
    public static class SpringConfig {

        @Bean
        public CourseController courseController() {
            return new CourseController(courseRepo(), teacherRepo());
        }

        @Bean
        public CourseRepo courseRepo() {
            CourseRepo mock = mock(CourseRepo.class);
            return mock;
        }

        @Bean
        public TeacherRepo teacherRepo() {
            TeacherRepo mock = mock(TeacherRepo.class);
            return mock;
        }
    }
}
