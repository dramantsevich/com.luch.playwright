package page;

import com.microsoft.playwright.Page;
import model.Product;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CartPage {
    private final Page page;

    private String listProducts = "//table[@id='basket_items']/tbody/tr";

    public CartPage(Page page){
        this.page = page;
    }

    public List getListProducts() { return page.querySelectorAll(listProducts); }

    public String getProductInCartByUrl(Product product){
        return page.getAttribute("//h2[@class='bx_ordercart_itemtitle']/a[@href='" +
                product.getProductURL() + "']", "href");
    }

    public void addMoreProductQuantity(Product product){
        page.click("//td[@class='item td-name']//a[@href='" +
                product.getProductURL() + "']/ancestor::td/following-sibling::td[@class='custom td-count']//a[@class='plus']");
    }

    public int getTotalSum() throws Throwable {
        String price = page.innerText("//div[@id='allSum_FORMATED']").replaceAll("\\s+", "");

        Pattern pattern = Pattern.compile("^[\\d]*");
        Matcher matcher = pattern.matcher(price);

        if (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();

            int totalPrice = Integer.parseInt(price.substring(start, end).trim());

            return totalPrice;
        } else {
            return 0;
        }
    }

    public int getQuantityCountByProduct(Product product) {
        int quantityCount = Integer.parseInt(page.getAttribute("//td[@class='item td-name']//a[@href='" +
                        product.getProductURL() + "']/ancestor::td/following-sibling::td[@class='custom td-count']//input[@type='hidden']",
                "value"));

        return quantityCount;
    }

    public void clickDeleteButtonByProduct(Product product){
        page.click("//td[@class='item td-name']//a[@href='" +
                product.getProductURL() + "']/ancestor::td/following-sibling::td[@class='custom td-count']/a[@class='delete__link']");
    }

    public String getEmptyCartMessage(){
        return page.innerText("p >> font");
    }
}
