package com.daniam;

public interface Mapping <E, D>{
    D toDto(E entity);
    E toEntity(D dto);
}
