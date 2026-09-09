package testdata.orangehrm;


public class EmployeeData {
    public static class NewEmployee {
        public static final String FIRST_NAME        = "John";
        public static final String LAST_NAME         = "Walker";

        public static final String EDIT_FIRST_NAME   = "Phillip";
        public static final String EDIT_LAST_NAME    = "Kennedy";

        public static final String USER_NAME         = "john.walker";
        public static final String PASSWORD          = "Auto123!@#";

        public static final String DATE_OF_BIRTH     = "2016-17-03";  // TODO: verify format (yyyy-dd-MM or yyyy-MM-dd?)
        public static final String GENDER            = "Female";
        public static final String MARITAL_STATUS    = "Single";
    }

    public static class EmployeeValidate {
        public static final String INVALID_PASSWORD   = "12345";
        public static final String INCORRECT_PASSWORD = "Auto1333!!!!";
    }
}
