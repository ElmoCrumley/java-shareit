package ru.practicum.shareit.user;

import java.util.Collection;
import java.util.Optional;

public interface UserService {
    public Optional<User> create(User user);

    public Collection<User> findAll();

    public Optional<User> findById(Long userId);

    public Optional<User> update(User user);

    public void delete(Long userId);
}
