package ru.practicum.shareit.item;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemMapper;
import ru.practicum.shareit.item.model.Item;

import java.util.*;

@Repository
public class ItemRepositoryImpl implements ItemRepository {
    private final Map<Long, Item> items = new HashMap<>();

    @Override
    public Optional<Item> create(Long userId, Item item) {
        Item responseIitem = new Item();

        responseIitem.setId(getNextId());
        responseIitem.setName(item.getName());
        responseIitem.setDescription(item.getDescription());
        responseIitem.setAvailable(item.getAvailable());
        responseIitem.setUserId(userId);
        items.put(responseIitem.getId(), responseIitem);
        return Optional.of(responseIitem);
    }

    @Override
    public Collection<ItemDto> findAll(Long userId) {
        return items.values().stream()
                .filter(item -> item.getUserId().equals(userId))
                .map(ItemMapper::toDtoItem)
                .toList();
    }

    @Override
    public Optional<Item> findById(Long userId, Long itemId) {
        return Optional.ofNullable(items.get(itemId));
    }

    @Override
    public Optional<Item> update(Long userId, ItemDto itemDto, Long itemId) {
        Item storageItem = items.get(itemId);

        if (storageItem == null) return Optional.empty();

        String name = itemDto.getName();
        String description = itemDto.getDescription();

        if (name != null) storageItem.setName(itemDto.getName());
        if (description != null) storageItem.setDescription(itemDto.getDescription());
        storageItem.setAvailable(itemDto.getAvailable());
        return Optional.of(storageItem);
    }

    @Override
    public Collection<ItemDto> search(Long userId, String text) {
        return items.values().stream()
                .filter(item -> Optional.ofNullable(item.getAvailable()).orElse(false)
                        && (Optional.ofNullable(item.getName())
                                                .map(name -> name.toLowerCase().contains(text.toLowerCase()))
                                                .orElse(false)
                                || Optional.ofNullable(item.getDescription())
                                                .map(name -> name.toLowerCase().contains(text.toLowerCase()))
                                                .orElse(false)
                        )
                )
                .map(ItemMapper::toDtoItem)
                .toList();
    }

    private long getNextId() {
        long currentMaxId = items.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }
}
