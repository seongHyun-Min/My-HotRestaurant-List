package com.example.restraunt.db;

import java.util.List;
import java.util.Optional;

public interface MemoryDbRepositoryIfs<T> {

    Optional<T> findById(int index); //인덱스를 받아서 해당 데이터를 리턴 하는 메소드를 만듬
    T save(T entity);
    void deletebyId(int index);
    List<T> listAll();
}
