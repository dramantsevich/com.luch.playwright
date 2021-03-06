package page;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import model.Order;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class OrderPage {
    private final Page page;

    private String clientNameInput = "input[name='ORDER_PROP_23']";
    private String clientSurnameInput = "input[name='ORDER_PROP_24']";
    private String clientPhoneNumberInput = "input[name='ORDER_PROP_17']";
    private String clientEmailInput = "input[name='ORDER_PROP_6']";
    private String clientCityInput = "input[class='bx-ui-sls-fake']";
    private String orderButton = "[class=' button _big _uppercase']";
    private String errorMessageList = "//p/font[@class='errortext']";
    private String errorMessage = "//div/p/font[@class='errortext']";
    private String orderFormContent = "#order_form_content";
    private String goToPaymentPageButton = "#ORDER_CONFIRM_BUTTON";
    private String cityFromDropDown = "//div[contains(@class,'dropdown-item bx-ui-sls-variant bx-ui-sls-variant-active')]/span[contains(text(),'%s')]";
    private String chooseService = "//div[contains(text(),'%s')]//ancestor::div[@class='input radio']/input";

    public OrderPage(Page page){ this.page = page; }


    public void inputAllFieldsInOrder(User user) {
        page.type(clientNameInput, user.getUsername(), new Page.TypeOptions().setDelay(100));
        page.type(clientSurnameInput, user.getUsername(), new Page.TypeOptions().setDelay(100));
        page.type(clientPhoneNumberInput, user.getPhone(), new Page.TypeOptions().setDelay(100));
        page.type(clientEmailInput, user.getEmail(), new Page.TypeOptions().setDelay(100));
        page.click(clientCityInput);
        page.type(clientCityInput, user.getCity(), new Page.TypeOptions().setDelay(100));
        clickCityFromDropDownList(user.getCity());
    }

    public void inputFieldsWithoutSurnameInOrder(User user) {
        page.type(clientNameInput, user.getUsername(), new Page.TypeOptions().setDelay(100));
        page.click(clientPhoneNumberInput);
        page.waitForTimeout(500);
        page.type(clientPhoneNumberInput, user.getPhone(), new Page.TypeOptions().setDelay(100));
        page.type(clientEmailInput, user.getEmail(), new Page.TypeOptions().setDelay(100));
        page.click(clientCityInput);
        page.type(clientCityInput, user.getCity(), new Page.TypeOptions().setDelay(100));
        clickCityFromDropDownList(user.getCity());
    }

    public void inputFieldsWithoutNameAndSurnameInOrder(User user) {
        page.type(clientPhoneNumberInput, user.getPhone(), new Page.TypeOptions().setDelay(100));
        page.click(clientEmailInput);
        page.waitForTimeout(500);
        page.type(clientEmailInput, user.getEmail(), new Page.TypeOptions().setDelay(100));
        page.click(clientCityInput);
        page.type(clientCityInput, user.getCity(), new Page.TypeOptions().setDelay(100));
        clickCityFromDropDownList(user.getCity());
    }

    private void clickCityFromDropDownList(String city) {
        page.click(String.format(cityFromDropDown, city));
        page.waitForTimeout(500);
    }

    public void chooseDeliveryService(Order.DeliveryService deliveryService) {
        page.check(String.format(chooseService, deliveryService.getValue()));
        page.isChecked(String.format(chooseService, deliveryService.getValue()));
    }

    public void choosePaymentSystem(Order.PaymentSystem paymentSystem) {
        page.check(String.format(chooseService, paymentSystem.getValue()));
        page.isChecked(String.format(chooseService, paymentSystem.getValue()));
        page.waitForTimeout(500);
    }

    public void goToPaymentPage() {
        page.click(goToPaymentPageButton);
        page.waitForTimeout(500);
    }

    public void goToOrderPage() {
        page.click(orderButton);
        page.waitForSelector(orderFormContent);
    }

    public List<String> getStringErrorMessageList() {
        List<String> errorMessages = new ArrayList<>();

        page.waitForSelector(errorMessageList);

        for(ElementHandle el : page.querySelectorAll(errorMessageList)) {
            errorMessages.add(el.innerText());
        }

        return errorMessages;
    }

    public List getErrorMessageList() {
        page.waitForSelector(errorMessageList);

        return page.querySelectorAll(errorMessageList);
    }

    public String getErrorMessage() { return page.innerText(errorMessage); }
}
