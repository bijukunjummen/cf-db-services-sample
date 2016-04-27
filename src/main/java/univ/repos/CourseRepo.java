package univ.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.domain.Course;

public interface CourseRepo extends JpaRepository<Course, Long> {
}
