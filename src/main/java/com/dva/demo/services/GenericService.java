package com.dva.demo.services;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GenericService<T> {
    List<T> findAll();

    List<T> findMarked(boolean deleted);

    T findById(Long id);

    T save(T object);

    void delete(Long id);
}
