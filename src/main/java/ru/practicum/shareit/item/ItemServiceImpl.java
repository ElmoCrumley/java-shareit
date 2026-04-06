package ru.practicum.shareit.item;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.InternalServerError;
import ru.practicum.shareit.exception.ItemCreationException;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.UserRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    ItemRepository itemRepository;
    UserRepository userRepository;

    public ItemServiceImpl(ItemRepository itemRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ItemDto create(Long userId, Item item) {
        try {
            Optional<Item> storageItem = itemRepository.create(userId, item);

            if (storageItem.isPresent()) {
                return ItemMapper.toDtoItem(storageItem.get());
            } else {
                throw new ItemCreationException("Item can't be created");
            }
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }

    @Override
    public Collection<ItemDto> findAll(Long userId) {
        try {
            return itemRepository.findAll(userId).stream()
                    .filter(item -> item.getUserId().equals(userId))
                    .map(ItemMapper::toDtoItem)
                    .toList();
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }

    @Override
    public ItemDto findById(Long userId, Long itemId) {
        try {
            Optional<Item> item = itemRepository.findById(userId, itemId);

            if (item.isPresent()) {
                return ItemMapper.toDtoItem(item.get());
            } else {
                throw new NotFoundException("Item wasn't found");
            }
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }

    @Override
    public ItemDto update(Long userId, ItemDto itemDto, Long itemId) {
        try {
            Optional<Item> item = itemRepository.update(userId, itemDto, itemId);

            if (item.isPresent()) {
                return ItemMapper.toDtoItem(item.get());
            } else {
                throw new NotFoundException("Item wasn't updated");
            }
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }

    public Collection<ItemDto> search(Long userId, String text) {
        try {
            return itemRepository.findAll(userId).stream()
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
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }
}
