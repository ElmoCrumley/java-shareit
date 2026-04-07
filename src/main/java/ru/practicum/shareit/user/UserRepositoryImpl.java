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
        return Optional.ofNullable(users.get(userId));
    }

    @Override
    public Optional<User> findByEmail(String userEmail) {
        for (User user : users.values()) {
            String email = user.getEmail();
            if (email != null && email.equals(userEmail)) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> update(Long userId, User user) {
        User storageUser = users.get(userId);
        if (storageUser == null) return Optional.empty();

        storageUser.setEmail(user.getEmail());
        storageUser.setName(user.getName());
        storageUser.setLogin(user.getLogin());
        return Optional.of(storageUser);
    }

    @Override
    public void delete(Long userId) {
        users.remove(userId);
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
