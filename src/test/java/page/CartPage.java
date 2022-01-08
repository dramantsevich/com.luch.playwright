package page;

import com.microsoft.playwright.Page;
import model.Product;

import java.util.List;

public class CartPage {
    private final Page page;

    private String listProducts = "//table[@id='basket_items']/tbody/tr";

    public CartPage(Page page){
        this.page = page;
    }

    public List getListProducts() { return page.querySelectorAll(listProducts); }

    public String getProductInCartByUrl(Product product){
        return page.getAttribute("//h2[@class='bx_ordercart_itemtitle']/a[@href='" + product.getProductURL() + "']", "href");
    }
}
