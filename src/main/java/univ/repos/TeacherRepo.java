package univ.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.domain.Teacher;

public interface TeacherRepo extends JpaRepository<Teacher, Long> {
}
