package me.thiagoleite.twchallenge.services;

import java.util.Optional;

public interface ApiService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    T create(T data);

    T update(Long id, T updatedData);

    void deleteById(Long id);
}
