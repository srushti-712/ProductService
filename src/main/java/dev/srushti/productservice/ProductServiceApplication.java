package dev.srushti.productservice;

import dev.srushti.productservice.models.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
        Product p1  = new Product();
        p1.getPrice();
        p1.getDescription();
    }

}
