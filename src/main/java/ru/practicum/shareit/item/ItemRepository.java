package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.Collection;
import java.util.Optional;

public interface ItemRepository {
    Optional<Item> create(Long userId, Item item);

    Collection<Item> findAll(Long userId);

    Optional<Item> findById(Long userId, Long itemId);

    Optional<Item> update(Long userId, ItemDto itemDto, Long itemId);
}
