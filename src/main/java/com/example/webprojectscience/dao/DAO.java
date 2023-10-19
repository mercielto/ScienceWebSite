package com.example.webprojectscience.dao;

import java.util.List;

public interface DAO<T> {
    List<T> getAll();
    T getById(Long id);
    T update(T entity);
    boolean delete(Long id);
    boolean insert(T entity);
}
