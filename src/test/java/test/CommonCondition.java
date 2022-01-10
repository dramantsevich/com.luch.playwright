package test;

import com.microsoft.playwright.*;
import org.testng.annotations.*;
import page.*;

public abstract class CommonCondition {
    private static Playwright playwright;
    private static Browser browser;
    private Page page;

    @BeforeClass
    static void setUpClass() {
        playwright = Playwright.create();
        BrowserType browserType = playwright.chromium();
        browser = browserType.launch(
                new BrowserType.LaunchOptions().setHeadless(false));
    }

    @BeforeMethod
    void setUp(){
        BrowserContext context = browser.newContext(
                new Browser.NewContextOptions().setViewportSize(1920,1080));
        page = context.newPage();
        page.navigate("https://luch.by/en/");
    }

    @AfterClass
    static void tearDownClass(){
        playwright.close();
    }

    @AfterMethod
    void tearDown(){
        page.context().close();
    }

    protected Page getPage(){
        return page;
    }

    protected MainPage getMainPage() { return new MainPage(getPage()); }

    protected ProductPage getProductPage() { return new ProductPage(getPage()); }

    protected Header getHeader() { return new Header(getPage()); }

    protected CartPage getCartPage() { return new CartPage(getPage()); }

    protected OrderPage getOrderPage() { return new OrderPage(getPage()); }

    protected PaymentPage getPaymentPage() { return new PaymentPage(getPage()); }

    protected CatalogPage getCatalogPage() { return new CatalogPage(getPage()); }

    protected CatalogAccessoriesPage getCatalogAccessoriesPage() { return new CatalogAccessoriesPage(getPage()); }
}
