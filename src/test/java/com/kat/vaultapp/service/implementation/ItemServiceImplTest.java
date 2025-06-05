package com.kat.vaultapp.service.implementation;

import com.kat.vaultapp.dto.item.ItemDto;
import com.kat.vaultapp.dto.item.ItemRequestDto;
import com.kat.vaultapp.entity.item.Item;
import com.kat.vaultapp.mapper.ItemMapper;
import com.kat.vaultapp.repository.ItemRepository;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ItemServiceImplTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ItemMapper itemMapper;

    @InjectMocks
    private ItemServiceImpl itemService;

    @Test
    public void test_should_return_list_of_items_when_findAll_is_called() {
        //given
        Item item1 = new Item();
        item1.setName("Item 1");
        Item item2 = new Item();
        item2.setName("Item 2");

        ItemDto itemDto1 = new ItemDto();
        itemDto1.setId(item1.getId());
        itemDto1.setName(item1.getName());

        ItemDto itemDto2 = new ItemDto();
        itemDto2.setId(item2.getId());
        itemDto2.setName(item2.getName());

        List<Item> items = List.of(item1, item2);
        Mockito.when(itemRepository.findAll()).thenReturn(items);
        Mockito.when(itemMapper.toDto(item1)).thenReturn(itemDto1);
        Mockito.when(itemMapper.toDto(item2)).thenReturn(itemDto2);

        //when
        List<ItemDto> expected = List.of(itemDto1, itemDto2);
        List<ItemDto> actual = itemService.findAll();

        //then
        for (int i = 0; i < expected.size(); i++) {
            Assertions.assertTrue(
                    EqualsBuilder.reflectionEquals(expected.get(i), actual.get(i)));
        }

    }

    @Test
    public void test_should_return_empty_list_when_findAll_is_called() {
        //given
        Mockito.when(itemRepository.findAll()).thenReturn(List.of());

        //when
        List<ItemDto> actual = itemService.findAll();

        //then
        Assertions.assertTrue(actual.isEmpty());

    }

    @Test
    public void test_should_return_true_when_item_is_saved() {
        //given
        ItemRequestDto requestDto = new ItemRequestDto("Test Item");
        Item item = new Item();
        item.setName(requestDto.name());

        ItemDto expected = new ItemDto();
        expected.setId(item.getId());
        expected.setName(item.getName());

        Mockito.when(itemMapper.toEntity(requestDto)).thenReturn(item);
        Mockito.when(itemRepository.save(item)).thenReturn(item);
        Mockito.when(itemMapper.toDto(item)).thenReturn(expected);

        //when
        ItemDto actual = itemService.save(requestDto);

        //then
        Assertions.assertTrue(EqualsBuilder.reflectionEquals(expected, actual));
    }

    @Test
    public void test_should_return_false_when_item_is_not_saved() {
        //given
        ItemRequestDto requestDto = new ItemRequestDto("Test Item");
        Item item = new Item();
        item.setName(requestDto.name());

        Mockito.when(itemMapper.toEntity(requestDto)).thenReturn(item);
        Mockito.when(itemRepository.save(item)).thenThrow(new RuntimeException("Save failed"));

        //when
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            itemService.save(requestDto);
        });

        //then
        Assertions.assertEquals("Save failed", exception.getMessage());
    }
}
