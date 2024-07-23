package com.daniam.shop.mapping;

import com.daniam.shop.domain.Store;
import com.daniam.shop.controller.dto.StoreCreateRequestDto;

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
