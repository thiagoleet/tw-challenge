package me.thiagoleite.twchallenge.controllers;

import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ApiController<T> {
    Iterable<T> getAll();

    ResponseEntity<T> getById(Long id);

    ResponseEntity<T> create(T data);

    ResponseEntity<T> update(Long id, T data);

    ResponseEntity<Void> remove(Long id);
}
