package dev.srushti.productservice.controllers;

import dev.srushti.productservice.dtos.CreateProductRequestDto;
import dev.srushti.productservice.dtos.ErrorDTO;
import dev.srushti.productservice.exceptions.ProductNotFoundException;
import dev.srushti.productservice.models.Product;
import dev.srushti.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    public ProductService productService;

    public ProductController(@Qualifier("selfProductService") ProductService productService) {
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
    public Product getSingleProduct(@PathVariable("id") long id) throws ProductNotFoundException {
        Product product = productService.getSingleProduct(id);
        if(product == null) throw new ProductNotFoundException("The product does not exist with the service");
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
    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto createProductRequestDto) {
        return productService.createProduct(createProductRequestDto.getTitle(),
                createProductRequestDto.getDescription(),
                createProductRequestDto.getPrice(),
                createProductRequestDto.getImage(),
                createProductRequestDto.getCategory());
    }

    /**
     *
     * @param createProductRequestDto
     * @param id
     * @return Product with the same id as in the path variable and the new attributes
     *
     */
    @PutMapping("/products/{id}")
    public Product updateProduct(@RequestBody CreateProductRequestDto createProductRequestDto, @PathVariable("id") int id) {
        return productService.replaceProduct(createProductRequestDto.getTitle(),
                createProductRequestDto.getDescription(),
                createProductRequestDto.getPrice(),
                createProductRequestDto.getImage(),
                createProductRequestDto.getCategory(),
                id);
    }

    @GetMapping("/products/paginated")
    public List<Product> getAllProductsPaginated(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize){
        Page<Product> products = productService.getAllProductsPaginated(pageNumber, pageSize);
        List<Product> productList = products.getContent();
        return productList;
    }

}

