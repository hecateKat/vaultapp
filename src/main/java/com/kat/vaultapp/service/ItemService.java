package com.kat.vaultapp.service;

import com.kat.vaultapp.dto.item.ItemDto;
import com.kat.vaultapp.dto.item.ItemRequestDto;
import java.util.List;

public interface ItemService {

    List<ItemDto> findAll();

    ItemDto save(ItemRequestDto requestDto);

}
