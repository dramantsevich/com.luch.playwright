package test;

import static org.assertj.core.api.Assertions.*;

import org.testng.annotations.Test;
import util.DataProvider;

import java.io.IOException;

public class FilterTests extends CommonCondition{
    @Test(dataProvider = "getTypeFilterData")
    public void checkCorrectTypeFilter(String typeName) {
        getCatalogWatchesPage().openPage();
        getCatalogWatchesPage().dropTypeFilter();
        getCatalogPage().ClickFilterByName(typeName);
        getCatalogPage().clickMoreInfoOfProduct();

        assertThat(getProductPage().getProductGender()).contains(typeName);
    }

    @Test(dataProvider = "getMovementFilterData")
    public void checkCorrectMovementFilter(String movementName) {
        getCatalogWatchesPage().openPage();
        getCatalogPage().ClickFilterByName(movementName);
        getCatalogPage().clickMoreInfoOfProduct();

        assertThat(getProductPage().getProductMovementType()).contains(movementName);
    }

    @org.testng.annotations.DataProvider
    public Object[] getTypeFilterData() throws IOException {
        return DataProvider.read("typeFilter");
    }

    @org.testng.annotations.DataProvider
    public Object [] getMovementFilterData() throws IOException {
        return DataProvider.read("movementFilter");
    }
}
