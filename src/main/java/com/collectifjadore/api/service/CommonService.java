package com.collectifjadore.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommonService<T> {
    void create(T t);



    void deleteById(String t);
    Page<T> getAll(Pageable page);


}