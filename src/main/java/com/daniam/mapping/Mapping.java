package com.daniam.mapping;

public interface Mapping <E, D>{
    D toDto(E entity);
    E toEntity(D dto);
}
