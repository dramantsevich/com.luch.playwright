package page;

import com.microsoft.playwright.Page;
import model.Product;

public class ProductPage {
    private final Page page;

    private String productPrice = "[itemprop='price']";
    private String addProductToOrderButton = "text=Add to cart";
    private String checkoutButton = "a:has-text('Checkout')";
    private String productType = "//div[@class='prop-title' and contains(text(),'Product type')]/following-sibling::div[@class='prop-value']/a";
    private String productColor = "//div[@class='prop-title' and contains(text(),'Colour')]/following-sibling::div[@class='prop-value']/a";
    private String productGenderType = "//div[@class='prop-title' and contains(text(),'Type')]/following-sibling::div[@class='prop-value']/a";
    private String productMovementType = "//div[@class='prop-title' and contains(text(),'Movement')]/following-sibling::div[@class='prop-value']/a";

    public ProductPage(Page page) {
        this.page = page;
    }

    public void openProductPage(String urlPart) {
        page.navigate("https://luch.by" + urlPart);
    }

    public String getCurrentUrl(){ return page.url(); }

    public Product setPriceProduct(Product product, int price) {
        product.setPrice(price);
        product.setCount(product.getCount() + 1);

        return product;
    }

    public void addToCart(Product product) {
        int price = Integer.parseInt(page.getAttribute(productPrice, "content"));
        setPriceProduct(product, price);

        page.click(addProductToOrderButton);
    }

    public CartPage goToCartPage() {
        page.click(checkoutButton);

        return new CartPage(page);
    }

    public String getProductType() {
        return page.innerText(productType);
    }

    public String getProductColor() {
        return page.innerText(productColor);
    }

    public String getProductGender() { return page.innerText(productGenderType); }

    public String getProductMovementType() { return page.innerText(productMovementType); }
}
