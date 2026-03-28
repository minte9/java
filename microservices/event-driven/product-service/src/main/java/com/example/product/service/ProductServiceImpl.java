package com.example.product.service;

import org.springframework.stereotype.Service;

import com.example.product.api.Product;
import com.example.product.event.EventPublisher;
import com.example.product.event.ProductCreateEvent;
import com.example.product.persistence.ProductMapper;
import com.example.product.persistence.ProductRepository;

import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final EventPublisher publisher;
    private final ProductMapper mapper;
    
    public ProductServiceImpl(
        ProductRepository repository, 
        EventPublisher publisher,
        ProductMapper mapper
    ) {
        this.repository = repository;
        this.publisher = publisher;
        this.mapper = mapper;
    }

    @Override
    public Mono<Product> create(Product product) {

        return repository.save(mapper.apiToEntity(product))
                .map(mapper::entityToApi)
                .doOnNext(saved -> 
                    publisher.publish(
                        new ProductCreateEvent(
                            saved.productId(),
                            saved.name(),
                            saved.weight()
                        )
                    )
                );
    }

    public void publishCreateEvent(Product product) {
        publisher.publish(new ProductCreateEvent(
            product.productId(),
            product.name(),
            product.weight()
        ));
    }

    @Override
    public Mono<Product> getProduct(int productId) {

        return repository.findByProductId(productId)
                .next()
                .map(e -> new Product(
                        e.productId(),
                        e.name(),
                        e.weight()
                ));
    }

    @Override
    public Mono<Void> delete(int productId) {
        return repository.deleteByProductId(productId);
    }
}
