package test;

import static org.assertj.core.api.Assertions.*;

import model.Order;
import model.Product;
import model.User;
import org.testng.annotations.Test;
import service.ProductCreator;
import service.UserCreator;

public class OrderPositiveTests extends PreConditionsForOrderTests {
    public static final String PRODUCT_FIRST_URL = "/en/kollektsii/vinil/95480726/";

    @Test
    public void checkCorrectlyOrderedProduct() {
        Product product = ProductCreator.product(PRODUCT_FIRST_URL);
        User testUser = UserCreator.userForOrderPage();
        String expectedMessage = "Order complete";

        preConditionProductInCart(product);
        getOrderPage().goToOrderPage();
        getOrderPage().inputAllFieldsInOrder(testUser);
        getOrderPage().chooseDeliveryService(Order.DeliveryService.PICKUP);
        getOrderPage().choosePaymentSystem(Order.PaymentSystem.CREDIT_CART_AND_APPLE_PAY);
        getOrderPage().goToPaymentPage();

        assertThat(getPaymentPage().getOrderCompeleteMessage()).isEqualTo(expectedMessage);
    }

}
