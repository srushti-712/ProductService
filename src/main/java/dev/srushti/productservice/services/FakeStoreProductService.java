package dev.srushti.productservice.services;

import dev.srushti.productservice.dtos.CreateProductRequestDto;
import dev.srushti.productservice.dtos.FakeStoreProductsDto;
import dev.srushti.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
        if(fakeStoreProductsDtoArr != null && fakeStoreProductsDtoArr.length > 0) {
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
    public Product createProduct(CreateProductRequestDto createProductRequestDto) {
        return null;
    }
}
