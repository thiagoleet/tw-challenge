package me.thiagoleite.twchallenge.controllers;

import java.util.Optional;

public interface ApiController<T> {
    Iterable<T> getAll();

    Optional<T> getById(Long id);

    T create(T data);

    T update(Long id, T data);

    void remove(Long id);
}
