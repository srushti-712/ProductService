package dev.srushti.productservice.services;

import dev.srushti.productservice.dtos.CreateProductRequestDto;
import dev.srushti.productservice.models.Product;
import java.util.List;
import dev.srushti.productservice.exceptions.ProductNotFoundException;
import org.springframework.data.domain.Page;


public interface ProductService  {
    List<Product> getAllProducts();

    Product getSingleProduct(long id) throws ProductNotFoundException;;
    Product createProduct(String title,
                          String description,
                          double price,
                          String imageUrl,
                          String category);

    Product replaceProduct(String title,
                          String description,
                          double price,
                          String imageUrl,
                          String category,
                          int id);

    Page<Product> getAllProductsPaginated(int pageNumber, int pageSize);

}
