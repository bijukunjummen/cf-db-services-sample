package univ.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.domain.Teacher;

import java.util.Optional;

public interface TeacherRepo extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByName(String name);
}
