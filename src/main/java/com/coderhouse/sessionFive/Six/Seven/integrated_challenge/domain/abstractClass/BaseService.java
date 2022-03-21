package com.coderhouse.sessionFive.Six.Seven.integrated_challenge.domain.abstractClass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

//Esta la uso para hacer la respuesta paginada
public abstract class BaseService<T, ID, R extends JpaRepository<T, ID>> {

    @Autowired
    protected R repository;

    public Page<T> findAll(Pageable pageable){
        return repository.findAll(pageable);
    }
}
