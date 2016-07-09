package dry1.services.DAO;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBConnectionDAO {
    Connection  getConnection() throws SQLException;
}
