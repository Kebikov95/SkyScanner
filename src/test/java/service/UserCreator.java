package service;

import model.User;

public class UserCreator {

    private static final String TESTDATA_USER_EMAIL = "test_user_email";
    private static final String TESTDATA_USER_PASSWORD = "test_user_password";

    private UserCreator() {}

    public static User withCredentialsFromProperty() {
        return new User(TestDataReader.getTestData(TESTDATA_USER_EMAIL),
                        TestDataReader.getTestData(TESTDATA_USER_PASSWORD));
    }

    public static User withEmptyUserName() {
        return new User("", TestDataReader.getTestData(TESTDATA_USER_PASSWORD));
    }

    public static User withEmptyUserPassword() {
        return new User(TestDataReader.getTestData(TESTDATA_USER_EMAIL), "");
    }
}
