package service;

import model.User;

public class UserCreator {
    public static final String TESTDATA_USER_NAME = "Dzmitrytest";
    public static final String TESTDATA_USER_PHONE = "123456789121";
    public static final String TESTDATA_USER_EMAIL = "test@test.by";
    public static final String TESTDATA_USER_CITY = "Minsk";

    public static User userForOrderPage() {
        return new User(TESTDATA_USER_NAME,
                TESTDATA_USER_PHONE,
                TESTDATA_USER_EMAIL,
                TESTDATA_USER_CITY);
    }
    public static User userForOnceClickOrder() {
        return new User(TESTDATA_USER_NAME,
                TESTDATA_USER_PHONE,
                TESTDATA_USER_EMAIL);
    }

    public static User userForOneClickOrderWithoutName() {
        return new User(TESTDATA_USER_PHONE,
                TESTDATA_USER_EMAIL);
    }

    public static User userForOneClickOrderWithoutPhone() {
        return new User(TESTDATA_USER_NAME);
    }
}
