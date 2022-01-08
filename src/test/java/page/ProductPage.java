package page;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;
import model.Product;

public class ProductPage {
    private final Page page;

//    private String productPrice = "meta[itemprop='priceCurrency']";//[@class="styled-price _red"]styled-price _red
//    private String productPrice = "[itemprop='price']";
//    private String addProductToOrderButton = "[class='button _big button_add']";
//    private String checkoutButton = "[contains(text(),'Checkout')]";

    public ProductPage(Page page){
        this.page = page;
    }

//    public ProductPage openProductPage(String urlPart){
////        page.waitForLoadState();
//        String url = "https://luch.by" + urlPart;
//        page.context().newPage().navigate(url);
////        page.route(url, route -> route.abort());
////        page.navigate("https://luch.by" + urlPart);
////        page.navigate("https://luch.by" + urlPart, new Page.NavigateOptions()
////                .setWaitUntil(WaitUntilState.NETWORKIDLE));
////        page.waitForNavigation(new Page.WaitForNavigationOptions().setUrl("https://luch.by" + urlPart), page::waitForLoadState);
//        return this;
//    }

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

    public static Product setPriceProduct(Product product, int price){
        product.setPrice(price);
        product.setCount(product.getCount() + 1);

        return product;
    }

    public static void addToCart(Product product, String productPrice){
        int price = Integer.parseInt(productPrice);
        setPriceProduct(product, price);


//        page.click(addProductToOrderButton);

//        return this;
    }

//    public void goToCartPage(){
//        page.click(checkoutButton);
//
////        return this;
//    }


}
