package com.daniam.mapping;

import com.daniam.domain.Store;
import com.daniam.controller.dto.StoreCreateRequestDto;

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
