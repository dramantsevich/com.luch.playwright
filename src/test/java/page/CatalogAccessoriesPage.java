package page;

import com.microsoft.playwright.Page;
import model.Product;
import service.GetListProducts;

import java.util.List;

public class CatalogAccessoriesPage extends CatalogPage implements GetListProducts {
    private final Page page;
    private final String BASE_URL = "https://luch.by/en/accessories/";

    public CatalogAccessoriesPage(Page page) {
        super(page);
        this.page = page;
    }

    public void openPage() {
        page.navigate(BASE_URL);
    }

    public List<Product> getListProducts() {
        List<Product> productsList = createListProducts();
        int index = 0;

        for(Product product : productsList) {
            setProductName(product, index);
            setProductPrice(product, index);

            index++;
        }

        return productsList;
    }
}
