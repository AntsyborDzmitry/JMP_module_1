package dry1.services;


public enum StudentSQLQueriesEnum {

    GET_STUDENT {
        @Override
        public String getQuery() {
            return "SELECT * FROM STUDENTS WHERE ID = ?";
        }
    },
    SAVE_STUDENT {
        @Override
        public String getQuery() {
            return "UPDATE STUDENTS SET NAME = ?, AGE = ? WHERE ID = ?";
        }
    },
    GET_ALL_STUDENTS {
        @Override
        public String getQuery() {
            return "SELECT * FROM STUDENTS";
        }
    },
    GET_STUDENT_BY_AGE {
        @Override
        public String getQuery() {
            return "SELECT * FROM STUDENTS WHERE AGE BETWEEN ? AND ?";
        }
    };

    public abstract String getQuery ();
}