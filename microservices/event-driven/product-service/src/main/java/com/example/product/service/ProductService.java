package com.example.product.service;

import com.example.product.api.Product;
import reactor.core.publisher.Mono;

public interface ProductService {

    Mono<Product> create(Product product);
    Mono<Product> getProduct(int productId);
    Mono<Void> delete(int productId);
}
