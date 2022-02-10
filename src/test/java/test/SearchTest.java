package test;

import org.testng.annotations.Test;
import util.DataProvider;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

public class SearchTest extends CommonCondition {
    @org.testng.annotations.DataProvider
    private Object [] getProductArticle() throws IOException {
        return DataProvider.read("productArticle");
    }

    @Test(dataProvider = "getProductArticle")
    public void checkCorrectSearch(String article) {
        getSearchPage().openPage();
        getSearchPage().enterSearchInput(article);
        getSearchPage().goToProduct();

        assertThat(getProductPage().getCurrentUrl()).contains(article);
    }
}
