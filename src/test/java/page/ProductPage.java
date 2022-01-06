package page;

import com.microsoft.playwright.Page;
import model.Product;

public class ProductPage {
    private final Page page;

    public ProductPage(Page page){
        this.page = page;
    }

    public ProductPage openProductPage(String urlPart){
        page.navigate("https://luch.by" + urlPart);
        return this;
    }

//    public ProductPage addToCart(Product product){
//
//    }

}
