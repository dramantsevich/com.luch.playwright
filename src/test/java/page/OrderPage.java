package page;

import com.microsoft.playwright.Page;
import model.Order;
import model.User;

public class OrderPage {
//    private final Page page;
//    private String clientNameInput = "input[name='ORDER_PROP_23']";
//    private String clientSurnameInput = "input[name='ORDER_PROP_24']";
//    private String clientPhoneNumberInput = "input[name='ORDER_PROP_17']";
//    private String clientEmailInput = "input[name='ORDER_PROP_6']";
//    private String loadingAPI = "//div[contains(@id,'wait_') and contains(text(),'Loading')]";
//    private String orderCompeleteMessage = "//div[@class='section-name _h3']";
//
//    public OrderPage(Page page){ this.page = page; }
//
//    public OrderPage inputAllFieldsInOrder(User user){
//        page.waitForSelector(clientNameInput);
//        page.type(clientNameInput, user.getUsername());
//        page.type(clientSurnameInput, user.getUsername());
//        page.type(clientPhoneNumberInput, user.getPhone());
//        page.type(clientEmailInput, user.getEmail());
//        clickCityFromDropDownList(user.getCity());
//
//        return this;
//    }
//
//    private void clickCityFromDropDownList(String city){
////        page.waitForSelector("//div[@class='bx-ui-sls-variants']");
//        page.click("//div[contains(@class,'dropdown-item bx-ui-sls-variant')]/span[contains(text(),'"+ city +"')]");
////        page.waitForSelector(loadingAPI);
//    }
//
//    public OrderPage chooseDeliveryService(Order.DeliveryService deliveryService) {
////        page.waitForSelector("//div[contains(text(),'"+ deliveryService.getValue() +"')]//ancestor::div[@class='input radio']/input");
//        page.click("//div[contains(text(),'"+ deliveryService.getValue() +"')]//ancestor::div[@class='input radio']/input");
////        page.waitForSelector(loadingAPI);
//
//        return this;
//    }
//
//    public OrderPage choosePaymentSystem(Order.PaymentSystem paymentSystem){
////        page.waitForSelector("//div[contains(text(),'"+ paymentSystem.getValue() +"')]//ancestor::div[@class='input radio']/input");
//        page.click("//div[contains(text(),'"+ paymentSystem.getValue() +"')]//ancestor::div[@class='input radio']/input");
////        page.waitForSelector(loadingAPI);
//
//        return this;
//    }
//
//    public void goToPaymentPage(){
//        page.click("//a[contains(text(),'Complete Order')]");
//    }
//
//    public String getOrderCompeleteMessage() { return page.innerText(orderCompeleteMessage); }
}
