package org.shop.model.input;

import lombok.Getter;
import lombok.Setter;
import org.shop.dao.entity.Product;

@Getter
@Setter
public class ProductInputModel {
    private String name;
    private int prise;
    private int quantity;
    private String description;
    private String photo;
    private Long categoryId;

    public static Product of(ProductInputModel model){
        return new Product(
                model.getName(),
                model.getPrise(),
                model.getQuantity(),
                model.getDescription(),
                model.getPhoto());
    }
}
