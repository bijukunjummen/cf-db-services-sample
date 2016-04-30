package univ.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.domain.Course;

import java.util.Optional;

public interface CourseRepo extends JpaRepository<Course, Long> {
    Optional<Course> findByCourseCode(String courseCode);
}
