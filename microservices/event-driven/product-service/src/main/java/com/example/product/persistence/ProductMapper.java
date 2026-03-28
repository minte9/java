package com.example.product.persistence;

import org.springframework.stereotype.Component;
import com.example.product.api.Product;

@Component
public class ProductMapper {
    
    public ProductEntity apiToEntity(Product product) {

        return new ProductEntity(
            null, // let Mongo generate _id
            product.productId(),
            product.name(),
            product.weight()
        );
    }

    public Product entityToApi(ProductEntity entity) {
        
        return new Product(
            entity.productId(),
            entity.name(),
            entity.weight()
        );
    }
}
