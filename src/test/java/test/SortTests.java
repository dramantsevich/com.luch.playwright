package test;

import static org.assertj.core.api.Assertions.*;

import model.Product;
import model.Sort;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortTests extends CommonCondition {
    @Test
    public void CheckSortLowestFirst() {
        getCatalogWatchesPage().openPage();
        getCatalogWatchesPage().clickSortButton();
        getCatalogWatchesPage().clickSortByName(Sort.SortCases.PRICE_LOWEST_FIRST);

        List<Product> productsList = getCatalogWatchesPage().getListProducts();
        List<Product> sortedList = new ArrayList<>(productsList);
        sortedList.sort(Comparator.comparing(Product::getPrice).reversed());

        assertThat(productsList).isEqualTo(sortedList);
    }

    @Test
    public void CheckSortHighestFirst() {
        getCatalogWatchesPage().openHighestFirstPage();
        getCatalogWatchesPage().getListProducts();

        List<Product> productsList = getCatalogWatchesPage().getListProducts();
        List<Product> sortedList = new ArrayList<>(productsList);
        sortedList.sort(Comparator.comparing(Product::getPrice));

        assertThat(productsList).isEqualTo(sortedList);
    }
}
