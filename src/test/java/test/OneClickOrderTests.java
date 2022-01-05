package test;

import model.User;
import org.testng.annotations.Test;
import page.MainPage;
import service.UserCreator;

import static org.testng.Assert.*;

public class OneClickOrderTests extends CommonCondition {
    MainPage mainPage = getMainPage();

    @Test
    public void checkCorrectOneClickOrder(){
        User testUser = UserCreator.userForOnceClickOrder();
        String expectedMessage = "Your message was sent successfully";


        mainPage = openPageAndClickToWatchesOneClickOrder()
                .inputFieldsInOneClickOrderPopup(testUser)
                .submitFormOneClickOrder();

        assertEquals(expectedMessage, mainPage.getFormOneClickOrderSuccessfullMessage());
    }

    @Test
    public void checkValidationErrorMessageIfNameIsEmpty(){
        User testUserWithoutName = UserCreator.userForOneClickOrderWithoutName();
        mainPage = openPageAndClickToWatchesOneClickOrder()
                .inputClientPhoneField(testUserWithoutName)
                .submitFormOneClickOrder();
    }

    @Test
    public void checkValidationErrorMessageIfPhoneIsEmpty(){
        User testUserWithoutName = UserCreator.userForOneClickOrderWithoutPhone();
        mainPage = openPageAndClickToWatchesOneClickOrder()
                .inputClientNameField(testUserWithoutName)
                .submitFormOneClickOrder();
    }

    private MainPage openPageAndClickToWatchesOneClickOrder() {
        return getMainPage()
                .clickWatchesToOneClickOrder(1);
    }
}
