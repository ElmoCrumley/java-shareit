package ru.practicum.shareit.item;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.Collection;
import java.util.Optional;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping("/items")
public class ItemController {
    ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<ItemDto> create(
            @RequestHeader("X-Sharer-User-Id") Long userId,
            @RequestBody ItemDto itemDto
    ) {
        return itemService.create(userId, itemDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<ItemDto> findAll(
            @RequestHeader("X-Sharer-User-Id") Long userId
    ) {
        return itemService.findAll(userId);
    }

    @GetMapping(value = "/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ItemDto> findById(
            @RequestHeader("X-Sharer-User-Id") Long userId,
            @PathVariable Long itemId
    ) {
        return itemService.findById(userId, itemId);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public Optional<ItemDto> update(
            @RequestHeader("X-Sharer-User-Id") Long userId,
            @RequestBody ItemDto itemDto
    ) {
        return itemService.update(userId, itemDto);
    }

    @GetMapping(value = "/search?text={text}")
    @ResponseStatus(HttpStatus.OK)
    public Collection<ItemDto> search(
            @RequestHeader("X-Sharer-User-Id") Long userId,
            @PathVariable String text
    ) {
        return itemService.search(userId, text);
    }
}
