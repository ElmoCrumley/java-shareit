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
    public static final String SHARER_USER_ID = "X-Sharer-User-Id";

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<Item> create(
            @RequestHeader(SHARER_USER_ID) Long userId,
            @RequestBody ItemDto itemDto
    ) {
        return itemService.create(userId, itemDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<Item> findAll(
            @RequestHeader(SHARER_USER_ID) Long userId
    ) {
        return itemService.findAll(userId);
    }

    @GetMapping(value = "/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Item> findById(
            @RequestHeader(SHARER_USER_ID) Long userId,
            @PathVariable Long itemId
    ) {
        return itemService.findById(userId, itemId);
    }

    @PatchMapping(value = "/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Item> update(
            @RequestHeader(SHARER_USER_ID) Long userId,
            @RequestBody ItemDto itemDto,
            @PathVariable Long itemId
    ) {
        return itemService.update(userId, itemDto, itemId);
    }

    @GetMapping(value = "/search")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Item> search(
            @RequestHeader(SHARER_USER_ID) Long userId,
            @RequestParam(name = "text") String searchString
    ) {
        return itemService.search(userId, searchString);
    }
}
