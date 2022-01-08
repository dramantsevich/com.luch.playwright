package test.Order;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import model.Order;
import model.Product;
import model.User;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import page.CartPage;
import page.Header;
import page.OrderPage;
import page.ProductPage;
import service.ProductCreator;
import test.CommonCondition;
import test.ProductTests;

import static org.assertj.core.api.Assertions.assertThat;

public class PreConditionsForOrderTests {
    private static Playwright playwright;
    private static Browser browser;
    private static Page page;
    BrowserContext context;
    public static final String PRODUCT_FIRST_URL = "/en/kollektsii/vinil/95480726/";

    @BeforeClass
    static void setUpClass() {
        playwright = Playwright.create();
        BrowserType browserType = playwright.chromium();
        browser = browserType.launch(
                new BrowserType.LaunchOptions().setHeadless(false));
    }

    protected void setUp(String urlPart) {
        context = browser.newContext(
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

    protected Header getHeader() {
        return new Header(getPage());
    }

//    protected OrderPage getOrderPage() { return new OrderPage(getPage()); }

    protected void preConditionProductInCart() throws Throwable {
        setUp(PRODUCT_FIRST_URL);
        Product product = ProductCreator.product(PRODUCT_FIRST_URL);
        ProductPage.addToCart(product, page.getAttribute("[itemprop='price']", "content"));
        page.click("[class='button _big button_add']");

        assertThat(getHeader().getIconProductInCartCount()).isGreaterThan(0);

        goToCartPage();
        goToOrderPage();
    }

    public void goToCartPage() {
        page.click("[class='btn btn-link product-item-detail-buy-button button _big']");

        page.waitForSelector("//table[@id='basket_items']/tbody/tr");
    }

    public void goToOrderPage(){
        page.click("[class=' button _big _uppercase']");

        page.waitForSelector("#order_form_content");
    }

    private String clientNameInput = "input[name='ORDER_PROP_23']";
    private String clientSurnameInput = "input[name='ORDER_PROP_24']";
    private String clientPhoneNumberInput = "input[name='ORDER_PROP_17']";
    private String clientEmailInput = "input[name='ORDER_PROP_6']";
    private String loading = "//div[contains(@id,'wait_') and contains(text(),'Loading')]";
    private String orderCompeleteMessage = "//div[@class='section-name _h3']";
    private String clientCityInput = "input[class='bx-ui-sls-fake']";

    public void inputAllFieldsInOrder(User user) throws InterruptedException {
        page.waitForSelector(clientNameInput);
        page.type(clientNameInput, user.getUsername(), new Page.TypeOptions().setDelay(100));
        page.click(clientSurnameInput);
        Thread.sleep(500);
        page.type(clientSurnameInput, user.getUsername(), new Page.TypeOptions().setDelay(100));
        page.type(clientPhoneNumberInput, user.getPhone(), new Page.TypeOptions().setDelay(100));
        page.type(clientPhoneNumberInput, user.getPhone(), new Page.TypeOptions().setDelay(100));
        page.click(clientSurnameInput);
        Thread.sleep(500);
        page.type(clientEmailInput, user.getEmail(), new Page.TypeOptions().setDelay(100));
        clickCityFromDropDownList(user.getCity());
    }

    private void clickCityFromDropDownList(String city) throws InterruptedException {
////        Locator locator = page.locator(clientCityInput);
////        locator.type(city, new Locator.TypeOptions().setDelay(100));
//        page.type(clientCityInput, city);
////        ElementHandle handle = page.querySelector("//div[@class='bx-ui-sls-variants']");
////        handle.waitForSelector("//div[@class='bx-ui-sls-variants']", new ElementHandle.WaitForSelectorOptions()
////                .setState(WaitForSelectorState.VISIBLE));
////        page.waitForSelector("//div[@class='bx-ui-sls-variants']", "block");
//        page.waitForSelector("//div[contains(@class,'dropdown-item bx-ui-sls-variant bx-ui-sls-variant-active')]/span");
//        page.click("//div[contains(@class,'dropdown-item bx-ui-sls-variant bx-ui-sls-variant-active')]/span[contains(text(),'"+ city +"')]");
////        page.waitForSelector(loadingAPI);
        page.click(":nth-match([placeholder=\"Enter name ...\"], 2)");
        page.type(":nth-match([placeholder=\"Enter name ...\"], 2)", city, new Page.TypeOptions().setDelay(100));
        page.click("//div[contains(@class,'dropdown-item bx-ui-sls-variant bx-ui-sls-variant-active')]/span[contains(text(),'"+ city +"')]");
        Thread.sleep(500);
    }

    public void chooseDeliveryService(Order.DeliveryService deliveryService, Order.DeliveryService deliveryServiceUnchecked) {
//        page.waitForSelector("//div[contains(text(),'"+ deliveryService.getValue() +"')]//ancestor::div[@class='input radio']/input");
        page.check("//div[contains(text(),'"+ deliveryService.getValue() +"')]//ancestor::div[@class='input radio']/input");
        page.isChecked("//div[contains(text(),'"+ deliveryService.getValue() +"')]//ancestor::div[@class='input radio']/input");
//        page.uncheck("//div[contains(text(),'"+ deliveryServiceUnchecked.getValue() +"')]//ancestor::div[@class='input radio']/input");
//        page.waitForSelector(loadingAPI);
    }

    public void choosePaymentSystem(Order.PaymentSystem paymentSystem, Order.PaymentSystem deliveryServiceUnchecked){
//        page.waitForSelector("//div[contains(text(),'"+ paymentSystem.getValue() +"')]//ancestor::div[@class='input radio']/input");
        page.check("//div[contains(text(),'"+ paymentSystem.getValue() +"')]//ancestor::div[@class='input radio']/input");
        page.isChecked("//div[contains(text(),'"+ paymentSystem.getValue() +"')]//ancestor::div[@class='input radio']/input");
//        page.uncheck("//div[contains(text(),'"+ deliveryServiceUnchecked.getValue() +"')]//ancestor::div[@class='input radio']/input");
//        page.waitForSelector(loadingAPI);
    }

    public void goToPaymentPage() throws InterruptedException {
        page.click("text=Complete Order");
    }

    public String getOrderCompeleteMessage() {
        page.waitForSelector(orderCompeleteMessage);
        return page.innerText(orderCompeleteMessage);
    }
}
