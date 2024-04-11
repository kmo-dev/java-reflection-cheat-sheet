package kmo.dev.service;

import kmo.dev.entity.Fruit;
import kmo.dev.repository.ProductRepository;

import java.util.List;

public class ProductService {

    private ProductRepository<Fruit> productRepository;

    public void buy(final Fruit product) {
        productRepository.save(product);
    }

    public void sell(final Fruit product) {
        productRepository.delete(product.getId());
    }

    public List<Fruit> findAll() {
        return productRepository.findAll();
    }

}
