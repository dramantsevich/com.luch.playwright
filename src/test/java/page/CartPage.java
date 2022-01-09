package page;

import com.microsoft.playwright.Page;
import model.Product;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CartPage {
    private final Page page;

    private String listProducts = "#basket_items > tbody > tr";
    private String errorMessage = "p >> font";

    public CartPage(Page page){
        this.page = page;
    }

    public List getListProducts() {
        page.waitForSelector(listProducts);

        return page.querySelectorAll(listProducts);
    }

    public String getProductInCartByUrl(Product product){
        return page.getAttribute("td > .bx_ordercart_itemtitle > a[href ='" +
                product.getProductURL() + "']", "href");
    }

    public void addMoreProductQuantity(Product product){
        page.click("//td[@class='item td-name']//a[@href='" +
                product.getProductURL() + "']/ancestor::td/following-sibling::td[@class='custom td-count']//a[@class='plus']");
    }

    public int getTotalSum() {
        String price = page.innerText("#allSum_FORMATED").replaceAll("\\s+", "");

        Pattern pattern = Pattern.compile("^[\\d]*");
        Matcher matcher = pattern.matcher(price);

        if (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();

            return Integer.parseInt(price.substring(start, end).trim());
        } else {
            return 0;
        }
    }

    public int getQuantityCountByProduct(Product product) {
        return Integer.parseInt(page.getAttribute("//td[@class='item td-name']//a[@href='" +
                        product.getProductURL() + "']/ancestor::td/following-sibling::td[@class='custom td-count']//input[@type='hidden']",
                "value"));
    }

    public void clickDeleteButtonByProduct(Product product){
        page.click("//td[@class='item td-name']//a[@href='" +
                product.getProductURL() + "']/ancestor::td/following-sibling::td[@class='custom td-count']/a[@class='delete__link']");
    }

    public String getEmptyCartMessage(){
        return page.innerText(errorMessage);
    }
}
