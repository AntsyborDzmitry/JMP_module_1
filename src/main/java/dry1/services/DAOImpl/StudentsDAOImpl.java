package dry1.services.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dry1.beans.Student;
import dry1.services.DAO.DBConnectionDAO;
import dry1.services.DAO.StudentsDAO;
import dry1.services.DBDAOFactory;
import dry1.services.QueryFactory;

public class StudentsDAOImpl implements StudentsDAO {
    private static final String GET_STUDENT = "GET_STUDENT";
    private static final String SAVE_STUDENT = "SAVE_STUDENT";
    private static final String GET_ALL_STUDENTS = "GET_ALL_STUDENTS";
    private static final String GET_STUDENT_BY_AGE = "GET_STUDENT_BY_AGE";

    private DBConnectionDAO connectionDAO = new DBDAOFactory().getDAO();;
    private QueryFactory qf = new QueryFactory();

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = qf.getQuery(GET_ALL_STUDENTS);
        try {
            connection = connectionDAO.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                students.add(createStudent(resultSet));
            }
        } catch (SQLException | IllegalArgumentException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                closeResultSet(resultSet);
                closePreparedStatement(preparedStatement);
                closeConnection(connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return students;
    }

    @Override
    public Student getStudent(long id) {
        Student student = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = qf.getQuery(GET_STUDENT);
        try {
            connection = connectionDAO.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                student = createStudent(resultSet);
            }
        } catch (SQLException | IllegalArgumentException  e) {
            throw new RuntimeException(e);
        } finally {
            try {
                closeResultSet(resultSet);
                closePreparedStatement(preparedStatement);
                closeConnection(connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return student;
    }

    @Override
    public void saveStudent(Student student) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = qf.getQuery(SAVE_STUDENT);
        try {
            connection = connectionDAO.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setLong(3, student.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | IllegalArgumentException  e) {
            throw new RuntimeException(e);
        } finally {
            try {
                closeResultSet(resultSet);
                closePreparedStatement(preparedStatement);
                closeConnection(connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Student> getStudentsByAge(int from, int to) {
        List<Student> students = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = qf.getQuery(GET_STUDENT_BY_AGE);
        try {
            connection = connectionDAO.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, from);
            preparedStatement.setInt(2, to);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                students.add(createStudent(resultSet));
            }
        } catch (SQLException | IllegalArgumentException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                closeResultSet(resultSet);
                closePreparedStatement(preparedStatement);
                closeConnection(connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return students;
    }

    private Student createStudent (ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setId(rs.getLong("ID"));
        student.setName(rs.getString("NAME"));
        student.setAge(rs.getInt("AGE"));

        return student;
    }

    private  void closeConnection (Connection cn) throws SQLException {
        if (cn != null) {
            cn.close();
        }
    }

    private  void closeResultSet (ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
    }

    private  void closePreparedStatement (PreparedStatement ps) throws SQLException {
        if (ps != null) {
            ps.close();
        }
    }
}
