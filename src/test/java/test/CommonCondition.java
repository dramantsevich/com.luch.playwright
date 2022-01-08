package test;

import com.microsoft.playwright.*;
import org.testng.annotations.*;
import page.*;

public class CommonCondition {
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
                new Browser.NewContextOptions().setViewportSize(1900,1000));
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

}
