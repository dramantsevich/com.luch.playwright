package test.Order;

import static org.assertj.core.api.Assertions.*;

import model.Order;
import model.User;
import org.testng.annotations.Test;
import page.OrderPage;
import service.UserCreator;

public class OrderPositiveTests extends PreConditionsForOrderTests{
//    OrderPage orderPage = getOrderPage();

    @Test
    public void checkCorrectlyOrderedProduct() throws Throwable {
        User testUser = UserCreator.userForOrderPage();
        String expectedMessage = "Order complete";

        preConditionProductInCart();
        inputAllFieldsInOrder(testUser);
        chooseDeliveryService(Order.DeliveryService.PICKUP, Order.DeliveryService.BY_POST);
        choosePaymentSystem(Order.PaymentSystem.CREDIT_CART_AND_APPLE_PAY, Order.PaymentSystem.ERIP);
//        orderPage.goToPaymentPage();
        goToPaymentPage();

        assertThat(getOrderCompeleteMessage()).isEqualTo(expectedMessage);
    }
}
