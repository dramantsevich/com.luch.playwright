package page;

import com.microsoft.playwright.Page;
import model.Product;

public class ProductPage {
    private final Page page;

//    private String productPrice = "meta[itemprop='priceCurrency']";//[@class="styled-price _red"]styled-price _red
    private String productPrice = "[itemprop='price']";
    private String addProductToOrderButton = "[class='button _big button_add']";
    private String checkoutButton = "[class='btn btn-link product-item-detail-buy-button button _big']";

    public ProductPage(Page page){
        this.page = page;
    }

    public void openProductPage(String urlPart){
        page.navigate("https://luch.by" + urlPart);
    }


//    public int getProductPrice(){
//        String str = page.getAttribute(productPrice, "content");
//        return Integer.parseInt(page.getAttribute(productPrice, "content"));
//    }
//    public String getProductPrice(){
////        String str = page.getAttribute(productPrice, "content");
////        ElementHandle element = page.querySelector(productPrice);
////        return element.getAttribute("content");
//        return page.getAttribute("[itemprop='price']", "content");
//    }

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

//    public void goToCartPage(){
//        page.click(checkoutButton);
//
////        return this;
//    }


}
