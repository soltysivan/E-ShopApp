package org.shop.model.input;

import lombok.Getter;
import lombok.Setter;
import org.shop.dao.entity.Category;

@Getter
@Setter
public class CategoryInputModel {

    private String name;
    private String description;

    public CategoryInputModel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static Category of(CategoryInputModel categoryInputModel){
        return new Category(
                categoryInputModel.getName(),
                categoryInputModel.getDescription()
        );
    }
}
