package ru.practicum.shareit.item;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.BadDataBody;
import ru.practicum.shareit.exception.InternalServerError;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService{
    ItemRepository itemRepository;
    UserRepository userRepository;

    public ItemServiceImpl(ItemRepository itemRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Item> create(Long userId, ItemDto itemDto) {
        try {
            Boolean isAvailable = itemDto.getAvailable();
            String name = itemDto.getName();
            String description = itemDto.getDescription();
            Optional<User> user = userRepository.findById(userId);

            if (userId == null) {
                throw new BadDataBody("The empty header userId");
            } else if (user.isEmpty()) {
                throw new NotFoundException("This user doesn't exist");
            } else if (isAvailable == null) {
                throw new BadDataBody("Item doesn't have an available");
            } else if (name == null || name.isEmpty()) {
                throw new BadDataBody("Item doesn't have a name");
            } else if (description == null || description.isEmpty()) {
                throw new BadDataBody("Item doesn't have a description");
            }

            return itemRepository.create(userId, itemDto);
        } catch (BadDataBody | NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }

    @Override
    public Collection<Item> findAll(Long userId) {
        try {
            if (userRepository.findById(userId).isEmpty()) throw new NotFoundException("This user doesn't exist");

            return itemRepository.findAll(userId);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }

    @Override
    public Optional<Item> findById(Long userId, Long itemId) {
        try {
            if (userRepository.findById(userId).isEmpty()) throw new NotFoundException("This user doesn't exist");

            return itemRepository.findById(userId, itemId);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }

    @Override
    public Optional<Item> update(Long userId, ItemDto itemDto, Long itemId) {
        try {
            if (userRepository.findById(userId).isEmpty())
                throw new NotFoundException("This user doesn't exist");

            return itemRepository.update(userId, itemDto, itemId);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }

    @Override
    public Collection<Item> search(Long userId, String text) {
        try {
            if (userRepository.findById(userId).isEmpty()) {
                throw new NotFoundException("This user doesn't exist");
            } else if (text.isEmpty()) {
                return Collections.emptyList();
            }

            return itemRepository.search(userId, text);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }
}
