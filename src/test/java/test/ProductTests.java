package test;

import static org.assertj.core.api.Assertions.*;

import model.Product;
import org.testng.annotations.*;
import service.ProductCreator;


public class ProductTests extends PreConditionsForOrderTests {
    public static final String PRODUCT_FIRST_URL = "/en/kollektsii/vinil/95480726/";

    @Test
    public void checkCorrectAddToCart() {
        Product product = ProductCreator.product(PRODUCT_FIRST_URL);

        preConditionProductInCart(product);

        assertThat(product.getCount()).isEqualTo(getCartPage().getListProducts().size());
        assertThat(product.getProductURL()).contains(getCartPage().getProductInCartByUrl(product));
    }

    @Test
    public void checkCorrectAddMoreProductQuantity() {
        Product product = ProductCreator.product(PRODUCT_FIRST_URL);

        preConditionProductInCart(product);
        getCartPage().addMoreProductQuantity(product);

        assertThat(product.getCount()).isEqualTo(getCartPage().getListProducts().size());
        assertThat(getCartPage().getTotalSum()).isEqualTo(product.getPrice() * getCartPage().getQuantityCountByProduct(product));
    }

    @Test
    public void checkCorrectDeleteProduct() {
        Product product = ProductCreator.product(PRODUCT_FIRST_URL);
        String expectedMessage = "YOUR SHOPPING CART IS EMPTY";

        preConditionProductInCart(product);
        getCartPage().clickDeleteButtonByProduct(product);

        assertThat(getCartPage().getEmptyCartMessage()).isEqualTo(expectedMessage);
    }
}
