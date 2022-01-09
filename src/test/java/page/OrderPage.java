package page;

import com.microsoft.playwright.Page;
import model.Order;
import model.User;

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
    private String errorMessage = "//p/font[@class='errortext']";
    private String orderFormContent = "#order_form_content";
    private String goToPaymentPageButton = "#ORDER_CONFIRM_BUTTON";

    public OrderPage(Page page){ this.page = page; }


    public void inputAllFieldsInOrder(User user){
        page.type(clientNameInput, user.getUsername(), new Page.TypeOptions().setDelay(100));
        page.type(clientSurnameInput, user.getUsername(), new Page.TypeOptions().setDelay(100));
        page.type(clientPhoneNumberInput, user.getPhone(), new Page.TypeOptions().setDelay(100));
        page.type(clientEmailInput, user.getEmail(), new Page.TypeOptions().setDelay(100));
        page.click(clientCityInput);
        page.type(clientCityInput, user.getCity(), new Page.TypeOptions().setDelay(100));
        clickCityFromDropDownList(user.getCity());
    }

    public void inputFieldsWithoutNameInOrder(User user){
        page.type(clientSurnameInput, user.getUsername(), new Page.TypeOptions().setDelay(100));
        page.type(clientPhoneNumberInput, user.getPhone(), new Page.TypeOptions().setDelay(100));
        page.type(clientEmailInput, user.getEmail(), new Page.TypeOptions().setDelay(100));
        page.click(clientCityInput);
        page.type(clientCityInput, user.getCity(), new Page.TypeOptions().setDelay(100));
        clickCityFromDropDownList(user.getCity());
    }

    private void clickCityFromDropDownList(String city){
        page.click("//div[contains(@class,'dropdown-item bx-ui-sls-variant bx-ui-sls-variant-active')]/span[contains(text(),'"+ city +"')]");
        page.waitForTimeout(500);
    }

    public void chooseDeliveryService(Order.DeliveryService deliveryService) {
        page.check("//div[contains(text(),'"+ deliveryService.getValue() +"')]//ancestor::div[@class='input radio']/input");
        page.isChecked("//div[contains(text(),'"+ deliveryService.getValue() +"')]//ancestor::div[@class='input radio']/input");
    }

    public void choosePaymentSystem(Order.PaymentSystem paymentSystem){
        page.check("//div[contains(text(),'"+ paymentSystem.getValue() +"')]//ancestor::div[@class='input radio']/input");
        page.isChecked("//div[contains(text(),'"+ paymentSystem.getValue() +"')]//ancestor::div[@class='input radio']/input");
        page.waitForTimeout(500);
    }

    public void goToPaymentPage() {
        page.click(goToPaymentPageButton);
    }

    public void goToOrderPage(){
        page.click(orderButton);
        page.waitForSelector(orderFormContent);
    }

    public List getErrorMessageList(){
        return page.querySelectorAll(errorMessageList);
    }

    public String getErrorMessage() { return page.innerText(errorMessage); }
}
