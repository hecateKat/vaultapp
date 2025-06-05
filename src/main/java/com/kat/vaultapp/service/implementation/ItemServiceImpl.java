package com.kat.vaultapp.service.implementation;

import com.kat.vaultapp.dto.item.ItemDto;
import com.kat.vaultapp.dto.item.ItemRequestDto;
import com.kat.vaultapp.entity.item.Item;
import com.kat.vaultapp.entity.user.User;
import com.kat.vaultapp.mapper.ItemMapper;
import com.kat.vaultapp.repository.ItemRepository;
import com.kat.vaultapp.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Override
    public List<ItemDto> findAll() {
        return itemRepository.findAll().stream()
                .map(itemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> findAllByUsername(String login) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return itemRepository.findAllByUser(user).stream()
                .map(itemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ItemDto save(ItemRequestDto requestDto, String login) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Item item = itemMapper.toEntity(requestDto);
        item.setUser(user);
        Item savedItem = itemRepository.save(item);
        return itemMapper.toDto(savedItem);
    }
}
