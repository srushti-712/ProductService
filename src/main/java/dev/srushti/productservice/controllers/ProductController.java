package dev.srushti.productservice.controllers;

import dev.srushti.productservice.dtos.CreateProductRequestDto;
import dev.srushti.productservice.models.Product;
import dev.srushti.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    public ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /*
    GET /products
     */
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    /*
    GET /product/{id}
     */
    @GetMapping("/products/{id}")
    public Product getSingleProduct(@PathVariable("id") long id) {
        return productService.getSingleProduct(id);
    }

     /*
    Create a product
    {
        title :
        description:
        price:
        category:
    } => payload / request body
    POST /products
     */
    @PostMapping
    public Product createProduct(@RequestBody CreateProductRequestDto createProductRequestDto) {
        return null;
    }

    public Product updateProduct(Product product) {
        return null;
    }

}

