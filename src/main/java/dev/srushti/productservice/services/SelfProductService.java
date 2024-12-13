package dev.srushti.productservice.services;

import dev.srushti.productservice.exceptions.ProductNotFoundException;
import dev.srushti.productservice.models.Category;
import dev.srushti.productservice.models.Product;
import dev.srushti.productservice.repositories.CategoryRepository;
import dev.srushti.productservice.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService {
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    public SelfProductService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Product getSingleProduct(long id) throws ProductNotFoundException {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(String title, String description, double price, String image_url, String category) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImage_url(image_url);
        product.setCreatedAt(new Date());
        product.setUpdatedAt(new Date());

//        product.setCategory();
        Category categoryObjFromDB = categoryRepository.findByTitle(category);
        if (categoryObjFromDB == null) {
            categoryObjFromDB = new Category();
            categoryObjFromDB.setTitle(category);
            categoryObjFromDB.setCreatedAt(new Date());
            categoryObjFromDB.setUpdatedAt(new Date());
            categoryRepository.save(categoryObjFromDB);
        }
        product.setCategory(categoryObjFromDB);
        productRepository.save(product);
        return product;
    }

    @Override
    public Product replaceProduct(String title, String description, double price, String image_url, String category, int id) {

        return null;
    }

    @Override
    public Page<Product> getAllProductsPaginated(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return productRepository.findAll(pageRequest);
    }
}
