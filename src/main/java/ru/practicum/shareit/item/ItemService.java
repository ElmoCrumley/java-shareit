package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.Collection;
import java.util.Optional;

public interface ItemService {
    Optional<Item> create(Long userId, ItemDto itemDto);

    Collection<Item> findAll(Long userId);

    Optional<Item> findById(Long userId, Long itemId);

    Optional<Item> update(Long userId, ItemDto itemDto, Long itemId);

    Collection<Item> search(Long userId, String text);
}
