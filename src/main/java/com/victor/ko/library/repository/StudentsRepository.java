package com.victor.ko.library.repository;

/**
 * class info here
 *
 * @author V.Kowalsky
 * @since December 31, 2017
 */

import com.victor.ko.library.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface StudentsRepository extends CrudRepository<Student, Long> {

    Collection<Student> findAll();

    @Query(value = "SELECT s.student_id, s.name FROM students s WHERE LOWER(s.name) LIKE LOWER(concat('%', :studentName,'%')) " +
            "OR s.student_id IN (" +
            "SELECT sb.student_id FROM students_books sb, books b WHERE b.book_id = sb.book_id " +
            "AND LOWER(b.serial_num) LIKE LOWER(concat('%', :bookSerialNum,'%') " +
            "))", nativeQuery = true)
    Collection<Student> getFilteredByStudentNameBookSerial(
            @Param("studentName") String studentName,
            @Param("bookSerialNum") String bookSerialNum);

    @Query("SELECT s FROM Student s WHERE s.name = ?1")
    Collection<Student> getByName(String studentName);

}