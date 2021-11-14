package edu.miu.ars.service;

import java.util.List;

public interface GenericService <T> {
    T save(T t);
    List<T> findAll();
    T findById(Long id);
}
