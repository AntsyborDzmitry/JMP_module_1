package dry1;

import java.util.List;

public interface StudentsDAO {
    List<Student> getAllStudents();
    Student getStudent(long id);
    void saveStudent(Student student);
    List<Student> getStudentsByAge(int from, int to);
}
