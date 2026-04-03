package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.Collection;
import java.util.Optional;

public interface ItemService {
    ItemDto create(Long userId, ItemDto itemDto);

    Collection<ItemDto> findAll(Long userId);

    ItemDto findById(Long userId, Long itemId);

    ItemDto update(Long userId, ItemDto itemDto, Long itemId);

    Collection<ItemDto> search(Long userId, String text);
}
