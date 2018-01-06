
CREATE TABLE students
(
   student_id BIGINT PRIMARY KEY auto_increment,
   name VARCHAR(64) NOT NULL
);

CREATE TABLE books
(
   book_id BIGINT PRIMARY KEY auto_increment,
   serial_num VARCHAR(64) NOT NULL,
   name VARCHAR(128) NOT NULL
);

CREATE TABLE students_books (
  student_id BIGINT NOT NULL REFERENCES students (student_id),
  book_id BIGINT NOT NULL REFERENCES books (book_id),
  PRIMARY KEY (student_id, book_id),
);
