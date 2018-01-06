INSERT INTO students(student_id, name) VALUES
  (1, 'Иванов'),(2, 'Петров'),(3, 'Сидоров');

INSERT INTO books(book_id, serial_num, name) VALUES
  (1, 'A11-11', 'Student Workbook Java in a Nutshell'),
  (2, 'B11-12', 'The Complete Reference, Ninth Edition'),
  (3, 'C11-13', 'The Java Language Specification'),
  (4, 'D11-14', 'Beginning Java'),
  (5, 'E11-15', 'Introduction to Java programming'),
  (6, 'F11-16', 'Test book 1'),
  (7, 'G11-17', 'Test book 2');

INSERT INTO students_books VALUES
  (1, 1),
  (1, 2),
  (2, 3),
  (2, 4),
  (2, 5),
  (3, 6),
  (3, 7);
