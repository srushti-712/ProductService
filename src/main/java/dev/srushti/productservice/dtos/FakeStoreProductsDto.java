package dev.srushti.productservice.dtos;

import dev.srushti.productservice.models.Category;
import dev.srushti.productservice.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FakeStoreProductsDto {
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;


    public Product toProduct() {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setDescription(description);

        Category category1 = new Category();
        category1.setTitle(category);

        product.setCategory(category1);
        product.setImageUrl(image);
        return product;

    }
}

