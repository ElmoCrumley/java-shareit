package ru.practicum.shareit.item;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.exception.BadDataBody;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.UserRepository;

import java.util.Collection;
import java.util.Collections;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping("/items")
public class ItemController {
    ItemService itemService;
    UserRepository userRepository;
    public static final String SHARER_USER_ID = "X-Sharer-User-Id";

    public ItemController(ItemService itemService, UserRepository userRepository) {
        this.itemService = itemService;
        this.userRepository = userRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto create(
            @RequestHeader(SHARER_USER_ID) Long userId,
            @RequestBody @Valid Item item
    ) {
        Boolean isAvailable = item.getAvailable();
        String name = item.getName();
        String description = item.getDescription();

        if (isAvailable == null) throw new BadDataBody("Item doesn't have an available");
        if (name == null || name.isEmpty()) throw new BadDataBody("Item doesn't have a name");
        if (description == null || description.isEmpty()) throw new BadDataBody("Item doesn't have a description");
        if (userId == null) throw new BadDataBody("The empty header userId");
        if (userRepository.findById(userId).isEmpty()) throw new NotFoundException("This user doesn't exist");

        return itemService.create(userId, item);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<ItemDto> findAll(
            @RequestHeader(SHARER_USER_ID) Long userId
    ) {
        if (userRepository.findById(userId).isEmpty()) throw new NotFoundException("This user doesn't exist");

        return itemService.findAll(userId);
    }

    @GetMapping(value = "/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public ItemDto findById(
            @RequestHeader(SHARER_USER_ID) Long userId,
            @PathVariable Long itemId
    ) {
        if (userRepository.findById(userId).isEmpty()) throw new NotFoundException("This user doesn't exist");

        return itemService.findById(userId, itemId);
    }

    @PatchMapping(value = "/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public ItemDto update(
            @RequestHeader(SHARER_USER_ID) Long userId,
            @RequestBody @Valid ItemDto itemDto,
            @PathVariable Long itemId
    ) {
        if (userRepository.findById(userId).isEmpty()) throw new NotFoundException("This user doesn't exist");

        return itemService.update(userId, itemDto, itemId);
    }

    @GetMapping(value = "/search")
    @ResponseStatus(HttpStatus.OK)
    public Collection<ItemDto> search(
            @RequestHeader(SHARER_USER_ID) Long userId,
            @RequestParam(name = "text") String searchString
    ) {
        if (userRepository.findById(userId).isEmpty()) throw new NotFoundException("This user doesn't exist");
        if (searchString.isEmpty()) return Collections.emptyList();

        return itemService.search(userId, searchString);
    }
}
