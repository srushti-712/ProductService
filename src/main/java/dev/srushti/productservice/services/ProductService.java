package dev.srushti.productservice.services;

import dev.srushti.productservice.dtos.CreateProductRequestDto;
import dev.srushti.productservice.models.Product;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product getSingleProduct(long id);
    Product createProduct(CreateProductRequestDto createProductRequestDto);
}
