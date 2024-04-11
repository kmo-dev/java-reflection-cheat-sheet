package kmo.dev.repository;

import kmo.dev.entity.Product;

import java.util.*;

public class ProductRepository<T extends Product> {

    private final Map<String, T> database = new HashMap<>();

    public void save(final T product) {
        database.put(product.getId(), product);
    }

    public void delete(final String id) {
        database.remove(id);
    }

    public Optional<T> find(final String id) {
        return Optional.ofNullable(database.get(id));
    }

    public List<T> findAll() {
        return new ArrayList<>(database.values());
    }
}
