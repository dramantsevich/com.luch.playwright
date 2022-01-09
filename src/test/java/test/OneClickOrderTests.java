package test;

import model.User;
import org.testng.annotations.Test;
import service.UserCreator;

import static org.testng.Assert.*;

public class OneClickOrderTests extends CommonCondition {
    @Test
    public void checkCorrectOneClickOrder(){
        User testUser = UserCreator.userForOnceClickOrder();
        String expectedMessage = "Your message was sent successfully";

        openPageAndClickToWatchesOneClickOrder();
        getMainPage().inputFieldsInOneClickOrderPopup(testUser);
        getMainPage().submitFormOneClickOrder();

        assertEquals(expectedMessage, getMainPage().getFormOneClickOrderSuccessfullMessage());
    }

    @Test
    public void checkValidationErrorMessageIfNameIsEmpty(){
        User testUserWithoutName = UserCreator.userForOneClickOrderWithoutName();

        openPageAndClickToWatchesOneClickOrder();
        getMainPage().inputClientPhoneField(testUserWithoutName);
        getMainPage().submitFormOneClickOrder();
    }

    @Test
    public void checkValidationErrorMessageIfPhoneIsEmpty(){
        User testUserWithoutName = UserCreator.userForOneClickOrderWithoutPhone();

        openPageAndClickToWatchesOneClickOrder();
        getMainPage().inputClientNameField(testUserWithoutName);
        getMainPage().submitFormOneClickOrder();
    }

    private void openPageAndClickToWatchesOneClickOrder() {
        getMainPage().clickWatchesToOneClickOrder(1);
    }
}
