package com.kat.vaultapp.mapper;

import com.kat.vaultapp.config.MapperConfig;
import com.kat.vaultapp.dto.item.ItemDto;
import com.kat.vaultapp.dto.item.ItemRequestDto;
import com.kat.vaultapp.entity.item.Item;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface ItemMapper {

    Item toEntity(ItemRequestDto requestDto);

    ItemDto toDto(Item item);

    ItemRequestDto toRequestDto(Item item);
}
