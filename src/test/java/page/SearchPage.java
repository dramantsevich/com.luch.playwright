package page;

import com.microsoft.playwright.Page;

public class SearchPage {
    private final Page page;
    private final String BASE_URL = "https://luch.by/en/search/";

    private String searchInput = "text=Enter search query Go >> input[name='q']";
    private String submitButton = "input:has-text('Go')";
    private String linkToProduct = "a.name";

    public SearchPage(Page page) { this.page = page; }

    public void openPage() { page.navigate(BASE_URL); }

    public void enterSearchInput(String string){
        page.type(searchInput, string);
        page.click(submitButton);
    }

    public void goToProduct(){
        page.click(linkToProduct);
    }
}
