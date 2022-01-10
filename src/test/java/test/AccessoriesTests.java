package test;


import static org.assertj.core.api.Assertions.*;

import model.Sort;
import org.testng.annotations.Test;
import util.DataProvider;

import java.io.IOException;


public class AccessoriesTests extends CommonCondition{
    @Test
    public void CheckSortFirstPopular(){
        getCatalogAccessoriesPage().openPage();
        getCatalogPage().clickSortButton();
        getCatalogPage().clickSortByName(Sort.SortCases.FIRST_POPULAR);

        assertThat(getCatalogAccessoriesPage().getListProducts().size()).isGreaterThan(0);
    }

    @Test(dataProvider = "getAccessoriesTypeData")
    public void checkCorrectAccessroiesProductType(String accessoriesProductType) {
        getCatalogAccessoriesPage().openPage();
        getCatalogPage().ClickFilterByName(accessoriesProductType);
        getCatalogPage().clickMoreInfoOfProduct();

        assertThat(getProductPage().getProductType()).contains(accessoriesProductType);
    }

    @Test(dataProvider = "getAccessoriesColorData")
    public void checkCheckCorrectAccessoriesColour(String accessoriesColor){
        getCatalogAccessoriesPage().openPage();
        getCatalogPage().ClickFilterByName(accessoriesColor);
        getCatalogPage().clickMoreInfoOfProduct();

        assertThat(getProductPage().getProductColor()).contains(accessoriesColor);
    }

    @org.testng.annotations.DataProvider
    public Object[] getAccessoriesTypeData() throws IOException {
        return DataProvider.read("accessoriesProductType");
    }

    @org.testng.annotations.DataProvider
    public Object [] getAccessoriesColorData() throws IOException {
        return DataProvider.read("accessoriesProductColor");
    }
}
