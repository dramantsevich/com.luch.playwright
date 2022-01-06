package test;

import model.Product;
import org.testng.annotations.Test;
import page.CartPage;
import page.ProductPage;
import service.ProductCreator;

public class ProductTests extends CommonCondition{
    public static final String PRODUCT_FIRST_URL = "/en/kollektsii/defender/77431556/";
    public static final String PRODUCT_SECOND_URL = "/en/kollektsii/obratnyy-khod/272081648/";

    ProductPage productPage = getProductPage();

    @Test
    public void checkCorrectAddToCart(){
        Product product = ProductCreator.product(PRODUCT_FIRST_URL);

    }

//    private CartPage preConditionProductInCart(Product product){
//        productPage = productPage
//                .openProductPage(product.getProductURL());
//        return
//    }
}
