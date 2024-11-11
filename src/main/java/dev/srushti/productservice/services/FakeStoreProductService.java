package dev.srushti.productservice.services;

import dev.srushti.productservice.dtos.CreateProductRequestDto;
import dev.srushti.productservice.dtos.FakeStoreProductsDto;
import dev.srushti.productservice.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {
    private final RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductsDto[] fakeStoreProductsDtoArr = restTemplate.getForObject("https://fakestoreapi.com/products/", FakeStoreProductsDto[].class);
        List<Product> products = new ArrayList<>();
        if(fakeStoreProductsDtoArr != null) {
            for (FakeStoreProductsDto fakeStoreProductsDto : fakeStoreProductsDtoArr) {
                products.add(fakeStoreProductsDto.toProduct());
            }
        }
        return products;
    }

    @Override
    public Product getSingleProduct(long id) {
        FakeStoreProductsDto fakeStoreProductsDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductsDto.class);
        return fakeStoreProductsDto.toProduct();
    }

    @Override
    public Product createProduct(String title,
                                 String description,
                                 double price,
                                 String imageUrl,
                                 String category) {
        FakeStoreProductsDto fakeStoreProductsDto = new FakeStoreProductsDto();
        fakeStoreProductsDto.setTitle(title);
        fakeStoreProductsDto.setDescription(description);
        fakeStoreProductsDto.setPrice(price);
        fakeStoreProductsDto.setImage(imageUrl);
        fakeStoreProductsDto.setCategory(category);

        FakeStoreProductsDto fakeStoreProductsDtoNew = restTemplate.postForObject("https://fakestoreapi.com/products", fakeStoreProductsDto, FakeStoreProductsDto.class);
        return fakeStoreProductsDtoNew.toProduct();
    }

    @Override
    public Product replaceProduct(String title, String description, double price, String imageUrl, String category, int id) {
        FakeStoreProductsDto fakeStoreProductsDto = new FakeStoreProductsDto();
        fakeStoreProductsDto.setTitle(title);
        fakeStoreProductsDto.setDescription(description);
        fakeStoreProductsDto.setPrice(price);
        fakeStoreProductsDto.setImage(imageUrl);
        fakeStoreProductsDto.setCategory(category);
        HttpEntity<FakeStoreProductsDto> entity = new HttpEntity<>(fakeStoreProductsDto);
        ResponseEntity<FakeStoreProductsDto> fakeStoreProductsDtoResponseEntity = restTemplate.exchange("https://fakestoreapi.com/products/{id}",
                HttpMethod.PUT,
                entity,
                FakeStoreProductsDto.class,id);
        Product product = fakeStoreProductsDtoResponseEntity.getBody().toProduct();
        product.setId(id);
        return product;
    }
}
