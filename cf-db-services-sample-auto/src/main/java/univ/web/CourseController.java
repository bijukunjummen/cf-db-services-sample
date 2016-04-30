package univ.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import univ.domain.Course;
import univ.domain.Student;
import univ.domain.Teacher;
import univ.repos.CourseRepo;
import univ.repos.StudentRepo;
import univ.repos.TeacherRepo;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseRepo courseRepo;
    private final TeacherRepo teacherRepo;
    private final StudentRepo studentRepo;

    @Autowired
    public CourseController(CourseRepo courseRepo, TeacherRepo teacherRepo, StudentRepo studentRepo) {
        this.courseRepo = courseRepo;
        this.teacherRepo = teacherRepo;
        this.studentRepo = studentRepo;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Course> save(@RequestBody @Valid Course course) {
        Teacher teacher = this.teacherRepo.findOne(course.getTeacher().getTeacherId());
        course.setTeacher(teacher);
        Course savedCourse = this.courseRepo.save(course);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Course> update(@RequestBody @Valid Course course) {
        Teacher teacher = this.teacherRepo.findOne(course.getTeacher().getTeacherId());
        course.setTeacher(teacher);
        Course savedCourse = this.courseRepo.save(course);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Course>> getPage(Pageable pageable) {
        Page<Course> page = this.courseRepo.findAll(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Course> get(@PathVariable("id") Long id) {
        Course course = this.courseRepo.findOne(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @RequestMapping(path = "/{courseId}/students", method = RequestMethod.GET)
    public ResponseEntity<Set<Student>> getStudentCourses(@PathVariable("courseId") Long courseId) {
        Course course = this.courseRepo.findOne(courseId);
        Set<Student> studentsForCourse = course.getStudents();//should resolve lazily
        return new ResponseEntity<>(studentsForCourse, HttpStatus.OK);
    }

    @RequestMapping(path = "/{courseId}/students", method = RequestMethod.PUT)
    public ResponseEntity<Student> addStudentToCourse(@RequestBody Student student, @PathVariable("courseId") Long courseId) {
        Course course = this.courseRepo.findOne(courseId);
        Student studentFromDb = this.studentRepo.findOne(student.getStudentId());
        studentFromDb.getCourses().add(course);
        Student updatedStudent  = this.studentRepo.save(student);
        return new ResponseEntity<>(updatedStudent, HttpStatus.ACCEPTED);
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        this.courseRepo.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
