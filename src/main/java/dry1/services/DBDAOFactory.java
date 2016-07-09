package dry1.services;

import dry1.services.DAO.DBConnectionDAO;
import dry1.services.DAOImpl.DBConnectionDAOSQLImpl;


public class DBDAOFactory {
    public DBDAOFactory() {
    }
    public DBConnectionDAO getDAO () {
        return new DBConnectionDAOSQLImpl();
    }
}
