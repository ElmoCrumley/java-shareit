package ru.practicum.shareit.user;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository {
    Optional<User> create(User user);

    Collection<User> findAll();

    Optional<User> findById(Long userId);

    Optional<User> findByEmail(String userEmail);

    Optional<User> update(User user);

    void delete(Long userId);
}
