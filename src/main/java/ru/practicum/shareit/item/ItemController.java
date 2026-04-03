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
    public ItemDto create(
            @RequestHeader(SHARER_USER_ID) Long userId,
            @RequestBody ItemDto itemDto
    ) {
        return itemService.create(userId, itemDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<ItemDto> findAll(
            @RequestHeader(SHARER_USER_ID) Long userId
    ) {
        return itemService.findAll(userId);
    }

    @GetMapping(value = "/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public ItemDto findById(
            @RequestHeader(SHARER_USER_ID) Long userId,
            @PathVariable Long itemId
    ) {
        return itemService.findById(userId, itemId);
    }

    @PatchMapping(value = "/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public ItemDto update(
            @RequestHeader(SHARER_USER_ID) Long userId,
            @RequestBody ItemDto itemDto,
            @PathVariable Long itemId
    ) {
        return itemService.update(userId, itemDto, itemId);
    }

    @GetMapping(value = "/search")
    @ResponseStatus(HttpStatus.OK)
    public Collection<ItemDto> search(
            @RequestHeader(SHARER_USER_ID) Long userId,
            @RequestParam(name = "text") String searchString
    ) {
        return itemService.search(userId, searchString);
    }
}
