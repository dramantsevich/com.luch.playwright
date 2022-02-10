package page;

import com.microsoft.playwright.Page;
import model.Product;
import service.GetListProducts;

import java.util.List;

public class CatalogWatchesPage extends CatalogPage implements GetListProducts {
    private final Page page;
    private final String BASE_URL = "https://luch.by/en/watches/";
    private final String HIGHEST_FIRST_URL = "https://luch.by/en/watches/?sort=PRICE&order=asc";

    private String typeFilterDropperButton = "text=Type >> div";

    public CatalogWatchesPage(Page page) {
        super(page);
        this.page = page;
    }

    public void openPage() {
        page.navigate(BASE_URL);
    }

    public void openHighestFirstPage() {
        page.navigate(HIGHEST_FIRST_URL);
    }

    public void dropTypeFilter() {
        page.click(typeFilterDropperButton);
    }

    @Override
    public List<Product> getListProducts() {
        List<Product> productsList = createListProducts();
        int index = 0;

        for(Product product : productsList) {
            setProductName(product, index);
            setProductArticle(product, index);
            setProductPrice(product, index);

            index++;
        }

        return productsList;
    }
}
