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
    private String totalSum = "#allSum_FORMATED";
    private String productInCart = "td > .bx_ordercart_itemtitle > a[href ='%s']";
    private String moreProductQuantity = "//td[@class='item td-name']//a[@href='%s']/ancestor::td/following-sibling::td[@class='custom td-count']//a[@class='plus']";
    private String quantityCount = "//td[@class='item td-name']//a[@href='%s']/ancestor::td/following-sibling::td[@class='custom td-count']//input[@type='hidden']";
    private String deleteButton = "//td[@class='item td-name']//a[@href='%s']/ancestor::td/following-sibling::td[@class='custom td-count']/a[@class='delete__link']";

    public CartPage(Page page) {
        this.page = page;
    }

    public List getListProducts() {
        page.waitForSelector(listProducts);

        return page.querySelectorAll(listProducts);
    }

    public String getProductInCartByUrl(Product product) {
        return page.getAttribute(String.format(productInCart, product.getProductURL()), "href");
    }

    public void addMoreProductQuantity(Product product) {
        page.click(String.format(moreProductQuantity, product.getProductURL()));
    }

    public int getTotalSum() {
        String price = page.innerText(totalSum).replaceAll("\\s+", "");

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
        return Integer.parseInt(page.getAttribute(String.format(quantityCount, product.getProductURL()), "value"));
    }

    public void clickDeleteButtonByProduct(Product product) {
        page.click(String.format(deleteButton, product.getProductURL()));
    }

    public String getEmptyCartMessage() {
        return page.innerText(errorMessage);
    }
}
