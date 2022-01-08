package page;

import com.microsoft.playwright.Page;

public class Header {
    private final Page page;

    private String iconProductsInCartCount = "//div[@class='shopping-cart !']/div[@class='count']";

    public Header(Page page){ this.page = page; }

    public int getIconProductInCartCount(){
        return Integer.parseInt(page.innerText(iconProductsInCartCount));
    }
}
