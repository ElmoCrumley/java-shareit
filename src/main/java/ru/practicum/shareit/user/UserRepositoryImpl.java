package ru.practicum.shareit.user;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final Map<Long, User> users = new HashMap<>();

    @Override
    public Optional<User> create(User user) {
        user.setId(getNextId());
        users.put(user.getId(), user);
        return Optional.of(user);
    }

    @Override
    public Collection<User> findAll() {
        return List.copyOf(users.values());
    }

    @Override
    public Optional<User> findById(Long userId) {
        return Optional.of(users.get(userId));
    }

    @Override
    public Optional<User> findByEmail(String userEmail) {
        for (User user : users.values()) {
            if (user.getEmail().equals(userEmail)) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> update(User user) {
        for (User storageUser : users.values()) {
            if (user.getId().equals(storageUser.getId())) {
                storageUser.setEmail(user.getEmail());
                storageUser.setName(user.getName());
                storageUser.setLogin(user.getLogin());
            }

            return Optional.of(storageUser);
        }

        return Optional.empty();
    }

    @Override
    public void delete(User user) {
        users.remove(user.getId());
    }

    private long getNextId() {
        long currentMaxId = users.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }
}
