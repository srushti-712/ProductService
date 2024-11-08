package dev.srushti.productservice.dtos;

import dev.srushti.productservice.models.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequestDto {
    private String title;
    private String description;
    private double price;
    private String image;
    private Category category;
}
