package univ.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.domain.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {
}
