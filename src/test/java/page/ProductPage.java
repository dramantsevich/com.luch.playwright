package page;

import com.microsoft.playwright.Page;
import model.Product;

public class ProductPage {
    private final Page page;

    private String productPrice = "[itemprop='price']";
    private String addProductToOrderButton = "text=Add to cart";
    private String checkoutButton = "a:has-text('Checkout')";

    public ProductPage(Page page){
        this.page = page;
    }

    public void openProductPage(String urlPart){
        page.navigate("https://luch.by" + urlPart);
    }

    public Product setPriceProduct(Product product, int price){
        product.setPrice(price);
        product.setCount(product.getCount() + 1);

        return product;
    }

    public void addToCart(Product product){
        int price = Integer.parseInt(page.getAttribute(productPrice, "content"));
        setPriceProduct(product, price);

        page.click(addProductToOrderButton);
    }

    public CartPage goToCartPage(){
        page.click(checkoutButton);

        return new CartPage(page);
    }
}
