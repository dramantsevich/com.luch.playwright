package page;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;

public class PaymentPage {
    private final Page page;

    private String orderCompeleteMessage = "//div[@class='section-name _h3']";

    public PaymentPage(Page page) {
        this.page = page;
        Response r = page.waitForResponse(response -> response.url().contains("ORDER_ID") && response.status() == 200, () -> {

        });
    }

    public String getOrderCompeleteMessage() {
        page.waitForSelector(orderCompeleteMessage);

        return page.innerText(orderCompeleteMessage);
    }
}
