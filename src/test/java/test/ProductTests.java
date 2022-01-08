package test;

import static org.assertj.core.api.Assertions.*;

import com.microsoft.playwright.*;
import model.Product;
import org.testng.annotations.*;
import page.CartPage;
import page.Header;
import page.OrderPage;
import page.ProductPage;
import service.ProductCreator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductTests {
    private static Playwright playwright;
    private static Browser browser;
    private static Page page;

    @BeforeClass
    static void setUpClass() {
        playwright = Playwright.create();
        BrowserType browserType = playwright.chromium();
        browser = browserType.launch(
                new BrowserType.LaunchOptions().setHeadless(false));
    }

    protected void setUp(String urlPart) {
        BrowserContext context = browser.newContext(
                new Browser.NewContextOptions().setViewportSize(1900, 1000));
        page = context.newPage();
        page.navigate("https://luch.by" + urlPart);
    }

    @AfterClass
    static void tearDownClass() {
        playwright.close();
    }

    @AfterMethod
    void tearDown() {
        page.context().close();
    }

    protected Page getPage() {
        return page;
    }

    protected ProductPage getProductPage() {
        return new ProductPage(getPage());
    }

    protected Header getHeader() {
        return new Header(getPage());
    }

    protected CartPage getCartPage() {
        return new CartPage(getPage());
    }

    public static final String PRODUCT_FIRST_URL = "/en/kollektsii/vinil/95480726/";
//    public static final String PRODUCT_SECOND_URL = "/en/kollektsii/nyud/395238287/";

    ProductPage productPage = getProductPage();

    @Test
    public void checkCorrectAddToCart() throws InterruptedException {
        setUp(PRODUCT_FIRST_URL);
//        String str = page.getAttribute("[itemprop='price']", "content");
//        Thread.sleep(5000);
        Product product = ProductCreator.product(PRODUCT_FIRST_URL);

//        CartPage cartPage = (CartPage) preConditionProductInCart(product);
        preConditionProductInCart(product);

        assertThat(product.getCount()).isEqualTo(getListProducts().size());
        assertThat(product.getProductURL()).contains(getProductInCartByUrl(product));
    }

    @Test
    public void checkCorrectAddMoreProductQuantity() throws Throwable {
        setUp(PRODUCT_FIRST_URL);
        Product product = ProductCreator.product(PRODUCT_FIRST_URL);

        preConditionProductInCart(product);
        addMoreQuantity(product);

        assertThat(product.getCount()).isEqualTo(getListProducts().size());
        assertThat(getTotalSum()).isEqualTo(product.getPrice() * getQuantityCountByProduct(product));
    }

    @Test
    public void checkCorrectDeleteProduct() throws Throwable {
        setUp(PRODUCT_FIRST_URL);
        Product product = ProductCreator.product(PRODUCT_FIRST_URL);
        String expectedMessage = "YOUR SHOPPING CART IS EMPTY";

        preConditionProductInCart(product);
        clickDeleteButtonByProduct(product);

        assertThat(getEmptyCartMessage()).isEqualTo(expectedMessage);
    }


    private void preConditionProductInCart(Product product) {
//        productPage = productPage
//                .openProductPage(product.getProductURL())
//                .addToCart(product, page.getAttribute("[itemprop='price']", "content"));
        ProductPage.addToCart(product, page.getAttribute("[itemprop='price']", "content"));
        page.click("[class='button _big button_add']");

        assertThat(getHeader().getIconProductInCartCount()).isGreaterThan(0);

        goToCartPage();

//        page.isVisible("[class='button _big button_add']");
//        productPage.goToCartPage();

//        return productPage.goToCartPage();
    }

    private String listProducts = "//table[@id='basket_items']/tbody/tr";

    public List getListProducts() {
        return page.querySelectorAll(listProducts);
    }

    public String getProductInCartByUrl(Product product) {
        return page.getAttribute("//h2[@class='bx_ordercart_itemtitle']/a[@href='" + product.getProductURL() + "']", "href");
    }

    public void goToCartPage() {
        page.click("[class='btn btn-link product-item-detail-buy-button button _big']");

        page.waitForSelector("//table[@id='basket_items']/tbody/tr");
    }

    public void addMoreQuantity(Product product) {
        page.click("//td[@class='item td-name']//a[@href='" +
                product.getProductURL() + "']/ancestor::td/following-sibling::td[@class='custom td-count']//a[@class='plus']");
    }

    public int getTotalSum() throws Throwable {
        Thread.sleep(500);
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

    public int getQuantityCountByProduct(Product product){
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
        return page.innerText("//p/font");
    }
}
