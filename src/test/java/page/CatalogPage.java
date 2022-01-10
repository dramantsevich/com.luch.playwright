package page;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import model.Product;
import model.Sort;
import service.ProductCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CatalogPage {
    private final Page page;

    private String sortButton = "text=Sort";
    private String itemList = ".item";
    private String itemNameList = "//a/div/div/div[@class='name']";
    private String itemPriceList = "//a/div/div/div/div/div[@class='price']/span";
    private String moreInfoButton = "text=More info";

    public CatalogPage(Page page) { this.page = page; }

    public void clickSortButton() {
        page.click(sortButton);
    }

    public void clickSortByName(Sort.SortCases sortName){
        page.click("a:has-text('" + sortName.getValue() + "')");
    }

    protected List<Product> createListProducts(){
        List<Product> productsList = new ArrayList<>();

        for(ElementHandle el : page.querySelectorAll(itemList)){
            Product product = ProductCreator.productFromCatalogPage();

            productsList.add(product);
        }

        return productsList;
    }

    protected Product setProductName(Product product, int index){
        List<ElementHandle> listName = page.querySelectorAll(itemNameList);
        product.setName(listName.get(index).innerText());

        return product;
    }

    protected Product setProductPrice(Product product, int index){
        List<ElementHandle> listPrice = page.querySelectorAll(itemPriceList);
        String price = listPrice.get(index).innerText().replaceAll("\\s+","");

        Pattern pattern = Pattern.compile("[^mr][\\d]+");
        Matcher matcher = pattern.matcher(price);

        if(matcher.find()){
            int start = matcher.start();
            int end = matcher.end();

            int itemPrice = Integer.parseInt(price.substring(start, end).trim());
            product.setPrice(itemPrice);

            return product;
        }
        else{
            return product;
        }
    }

    public void ClickFilterByName(String filterName){
        page.click("label:has-text('" + filterName + "')");
        page.waitForTimeout(750);
    }

    public void clickMoreInfoOfProduct(){
        page.click(moreInfoButton);
    }
}
