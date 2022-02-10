package test;

import static org.assertj.core.api.Assertions.*;

import model.Product;
import model.User;
import org.testng.annotations.Test;
import service.ProductCreator;
import service.UserCreator;

import java.util.Arrays;
import java.util.List;

public class OrderNegativeTests extends PreConditionsForOrderTests{
    public static final String PRODUCT_FIRST_URL = "/en/kollektsii/vinil/95480726/";

    @Test
    public void checkNotEnteredAllFieldsOnOrderedProduct() {
        int expectedCount = 6;
        Product product = ProductCreator.product(PRODUCT_FIRST_URL);

        preConditionProductInCart(product);
        getOrderPage().goToOrderPage();
        getOrderPage().goToPaymentPage();

        assertThat(getOrderPage().getErrorMessageList().size()).isEqualTo(expectedCount);
    }

    @Test
    public void checkNotEnteredSurnameFieldOnOrderedProduct() {
        User testUser = UserCreator.userForOrderPage();
        Product product = ProductCreator.product(PRODUCT_FIRST_URL);
        String expectedError = "Фамилия this field is required";

        preConditionProductInCart(product);
        getOrderPage().goToOrderPage();
        getOrderPage().inputFieldsWithoutSurnameInOrder(testUser);
        getOrderPage().goToPaymentPage();

        assertThat(getOrderPage().getErrorMessage()).isEqualTo(expectedError);
    }

    @Test
    public void checkNotEnteredNameAndSurnameFieldOnOrderedProduct() {
        User testUser = UserCreator.userForOrderPage();
        Product product = ProductCreator.product(PRODUCT_FIRST_URL);
        List<String> expectedErrors = Arrays.asList("Имя this field is required", "Фамилия this field is required");

        preConditionProductInCart(product);
        getOrderPage().goToOrderPage();
        getOrderPage().inputFieldsWithoutNameAndSurnameInOrder(testUser);
        getOrderPage().goToPaymentPage();

        List<String> actualErrors = getOrderPage().getStringErrorMessageList();

        assertThat(actualErrors.size()).isEqualTo(expectedErrors.size());
        assertThat(actualErrors.get(0)).isEqualTo(expectedErrors.stream().findFirst().map(Object::toString).orElse(null));
        assertThat(actualErrors.get(1)).isEqualTo(expectedErrors.get(expectedErrors.size() - 1));
    }
}
