package test;

import com.microsoft.playwright.*;
import model.Browsers;
import org.testng.annotations.*;
import page.*;

import static model.Browsers.CHROME;

public class CommonCondition {
    Playwright playwright;
    Browser browser;
    Page page;
    BrowserContext context;
    protected static String BROWSER = System.getProperty("browser", CHROME.name());

    @BeforeClass
    void setUpClass() {
        if(System.getProperty("browser") != null) {
            BROWSER = Browsers.valueOf(System.getProperty("browser").toUpperCase()).name();
        }
        switch (BROWSER) {
            case "CHROME": {
                playwright = Playwright.create();
                BrowserType browserType = playwright.chromium();
                browser = browserType.launch(
                        new BrowserType.LaunchOptions().setHeadless(false));
                break;
            }
            case "FIREFOX": {
                playwright = Playwright.create();
                BrowserType browserType = playwright.firefox();
                browser = browserType.launch(
                        new BrowserType.LaunchOptions().setHeadless(false));
                break;
            }
            case "EDGE": {
                playwright = Playwright.create();
                BrowserType browserType = playwright.chromium();
                browser = browserType.launch(
                        new BrowserType.LaunchOptions().setHeadless(false).setChannel("msedge"));
                break;
            }
            default:
                throw new IllegalArgumentException(System.getProperty("browser"));
        }
    }

    @BeforeMethod
    void setUp() {
        context = browser.newContext(
                new Browser.NewContextOptions().setViewportSize(1920,1080));
        page = context.newPage();
        page.navigate("https://luch.by/en/");
    }

    @AfterClass
    void tearDownClass() {
        playwright.close();
    }

    @AfterMethod
    void tearDown() {
        context.close();
    }

    protected Page getPage() {
        return page;
    }

    protected MainPage getMainPage() {
        return new MainPage(getPage());
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

    protected OrderPage getOrderPage() {
        return new OrderPage(getPage());
    }

    protected PaymentPage getPaymentPage() {
        return new PaymentPage(getPage());
    }

    protected CatalogPage getCatalogPage() {
        return new CatalogPage(getPage());
    }

    protected CatalogAccessoriesPage getCatalogAccessoriesPage() {
        return new CatalogAccessoriesPage(getPage());
    }

    protected CatalogWatchesPage getCatalogWatchesPage() {
        return new CatalogWatchesPage(getPage());
    }

    protected SearchPage getSearchPage() {
        return new SearchPage(getPage());
    }
}
