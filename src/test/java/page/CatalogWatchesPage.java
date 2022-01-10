package page;

import com.microsoft.playwright.Page;

public class CatalogWatchesPage extends CatalogPage{
    private final Page page;
    private final String BASE_URL = "https://luch.by/en/watches/";

    private String typeFilterDropperButton = "text=Type >> div";

    public CatalogWatchesPage(Page page) {
        super(page);
        this.page = page;
    }

    public void openPage() {
        page.navigate(BASE_URL);
    }

    public void dropTypeFilter() {
        page.click(typeFilterDropperButton);
    }

}
