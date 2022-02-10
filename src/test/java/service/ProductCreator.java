package service;

import model.Product;

public class ProductCreator {

    public static Product product(String productURL) {
        return new Product(productURL, 0);
    }

    public static Product productFromCatalogPage() {
        return new Product(null, 0, 0);
    }
}
