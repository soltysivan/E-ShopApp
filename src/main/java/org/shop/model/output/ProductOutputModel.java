package org.shop.model.output;

import lombok.Getter;
import org.shop.dao.entity.Product;

@Getter
public class ProductOutputModel {
    private final Long id;
    private final String name;
    private final int prise;
    private final int quantity;
    private final String description;
    private final String photo;
    private final Long categoryId;

    public ProductOutputModel(Long id, String name, int prise, int quantity, String description, String photo, Long categoryId) {
        this.id = id;
        this.name = name;
        this.prise = prise;
        this.quantity = quantity;
        this.description = description;
        this.photo = photo;
        this.categoryId = categoryId;
    }

    public static ProductOutputModel of(Product product){
        return new ProductOutputModel(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.getDescription(),
                product.getPhoto(),
                product.getCategory().getId()
        );
    }
}
