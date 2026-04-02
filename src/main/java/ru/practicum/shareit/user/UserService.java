package ru.practicum.shareit.user;

import java.util.Collection;
import java.util.Optional;

public interface UserService {
    Optional<User> create(User user);

    Collection<User> findAll();

    Optional<User> findById(Long userId);

    Optional<User> update(Long userId, User user);

    void delete(Long userId);
}
