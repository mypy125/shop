package com.daniam;

import com.daniam.dto.StoreCreateRequestDto;

public class StoreMapping implements Mapping<Store, StoreCreateRequestDto> {
    @Override
    public StoreCreateRequestDto toDto(Store entity) {
        return null;
    }

    @Override
    public Store toEntity(StoreCreateRequestDto dto) {
        return new Store(
                dto.getPassword(),
                dto.getName(),
                dto.getAddress(),
                dto.getProducts()
        );
    }
}
