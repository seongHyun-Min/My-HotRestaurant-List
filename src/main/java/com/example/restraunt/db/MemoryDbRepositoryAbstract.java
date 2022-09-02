package com.example.restraunt.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

abstract public class MemoryDbRepositoryAbstract<T extends MemoryDbEntity> implements  MemoryDbRepositoryIfs<T>{ //추상 메서드 생성

    private final List<T> db = new ArrayList<>();
    private int index =0; //autogenerics




    @Override
    public Optional<T> findById(int index) {
    return db.stream().filter(it -> it.getIndex() == index).findFirst();
    }

    @Override
    public T save(T entity) {

        var optionalEntity = db.stream().filter(it -> it.getIndex() == entity.getIndex()).findFirst();

        if(optionalEntity.isEmpty()){
            //db 에 데이터가 없는경우
            index ++;
            entity.setIndex(index);
            db.add(entity);
            return entity;
        }else {        //db 에 데이터가 없는경우
            // 업데이트
            // 카운트가 늘어나면 안된다

            var preIndex = optionalEntity.get().getIndex();
            entity.setIndex(preIndex);

            deletebyId(preIndex);
            db.add(entity);
            return entity;
        }

    }

    @Override
    public void deletebyId(int index) {
        var optionalEntity = db.stream().filter(it -> it.getIndex() ==index).findFirst();
        if(optionalEntity.isPresent()){
            db.remove(optionalEntity.get());
        }

    }

    @Override
    public List<T> listAll() {
        return db;
    }
}
