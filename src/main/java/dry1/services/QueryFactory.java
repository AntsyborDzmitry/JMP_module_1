package dry1.services;


public class QueryFactory {
    public QueryFactory() {
    }
    public String getQuery (String queryName) throws IllegalArgumentException {
        StudentSQLQueriesEnum queryByName = StudentSQLQueriesEnum.valueOf(queryName);
        return queryByName.getQuery();
    }
}
