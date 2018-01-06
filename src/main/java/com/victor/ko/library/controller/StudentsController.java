package com.victor.ko.library.controller;

/**
 * class info here
 *
 * @author V.Kowalsky
 * @since December 31, 2017
 */

import com.victor.ko.library.model.Book;
import com.victor.ko.library.model.Student;
import com.victor.ko.library.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/students")
public class StudentsController {
    @Autowired
    private StudentsRepository studentsRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Student>> getStudents() {
        return new ResponseEntity<>(studentsRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Student> getStudent(@PathVariable long id) {
        Student student = studentsRepository.findOne(id);

        if (student != null) {
            return new ResponseEntity<>(studentsRepository.findOne(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addStudent(@Valid @RequestBody Student studentParam, BindingResult result) {
        Collection<Student> students = studentsRepository.getByName(studentParam.getName());

        if (!students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        List<FieldError> errors = result.getFieldErrors();

        if (!errors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(studentsRepository.save(studentParam), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Student> updateStudent(@PathVariable long id, @RequestBody Student student) {
        Student currentStudent = studentsRepository.findOne(id);

        if (currentStudent==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentStudent.setName(student.getName());
        currentStudent.setBooks(student.getBooks());

        return new ResponseEntity<>(studentsRepository.save(currentStudent), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
        studentsRepository.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/books", method = RequestMethod.GET)
    public ResponseEntity<Set<Book>> getStudentBooks(@PathVariable long id) {
        Student student = studentsRepository.findOne(id);

        if (student != null) {
            return new ResponseEntity<>(student.getBooks(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/filtered", method = RequestMethod.GET)
    public ResponseEntity<Collection<Student>> test(@RequestParam("query") String studentName,
                                                    @RequestParam("key") String bookSerial){
        Collection<Student> filteredStudents = studentsRepository.getFilteredByStudentNameBookSerial(studentName, bookSerial);
        ResponseEntity responseEntity = new ResponseEntity<>(filteredStudents, HttpStatus.OK);
        return responseEntity;
    }

}
