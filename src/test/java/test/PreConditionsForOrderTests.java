package test;

import model.Product;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class PreConditionsForOrderTests extends CommonCondition {
    protected void preConditionProductInCart(Product product){
        getProductPage().openProductPage(product.getProductURL());
        getProductPage().addToCart(product);

        assertThat(getHeader().getIconProductInCartCount()).isGreaterThan(0);

        getProductPage().goToCartPage();
    }
}
