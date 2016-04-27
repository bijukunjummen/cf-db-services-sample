package univ.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import univ.domain.Teacher;
import univ.repos.TeacherRepo;

import javax.validation.Valid;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherRepo teacherRepo;

    @Autowired
    public TeacherController(TeacherRepo teacherRepo) {
        this.teacherRepo = teacherRepo;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Teacher> save(@RequestBody @Valid Teacher teacher) {
        Teacher savedTeacher = this.teacherRepo.save(teacher);
        return new ResponseEntity<>(savedTeacher, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Teacher> update(@RequestBody @Valid Teacher teacher) {
        Teacher savedTeacher = this.teacherRepo.save(teacher);
        return new ResponseEntity<>(savedTeacher, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Teacher>> getPage(Pageable pageable) {
        Page<Teacher> page = this.teacherRepo.findAll(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Teacher> get(@PathVariable("id") Long id) {
        Teacher teacher = this.teacherRepo.findOne(id);
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        this.teacherRepo.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
