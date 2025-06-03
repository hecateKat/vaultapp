package com.kat.vaultapp.service.implementation;

import com.kat.vaultapp.dto.item.ItemDto;
import com.kat.vaultapp.dto.item.ItemRequestDto;
import com.kat.vaultapp.entity.item.Item;
import com.kat.vaultapp.mapper.ItemMapper;
import com.kat.vaultapp.repository.ItemRepository;
import com.kat.vaultapp.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;


    @Override
    public List<ItemDto> findAll() {
        return itemRepository.findAll().stream()
                .map(itemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ItemDto save(ItemRequestDto requestDto) {
        Item item = itemMapper.toEntity(requestDto);
        Item savedItem = itemRepository.save(item);
        return itemMapper.toDto(savedItem);
    }
}
