package com.example.product.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class ProductEntity {

    @Id 
    private String id;

    private int productId;
    private String name;
    private int weight;

    public ProductEntity() {}

    public ProductEntity(int productId, String name, int weight) {
        this.productId = productId;
        this.name = name;
        this.weight = weight;
    }

    public String getId() { return id; }
    public int getProductId() { return productId; }
    public String getName() { return name; }
    public int getWeight() { return weight; }
}