package ru.practicum.shareit.user;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.BadDataBody;
import ru.practicum.shareit.exception.Conflict;
import ru.practicum.shareit.exception.InternalServerError;
import ru.practicum.shareit.exception.NotFoundException;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> create(User user) {
        try {
            String email = user.getEmail();

            if (email == null || email.isEmpty()) {
                throw new BadDataBody("Empty email in the body's request");
            } else if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                throw new Conflict("This email is already exist");
            }

            return userRepository.create(user);
        } catch (Exception e) {
            throw new InternalServerError("An unexpected error occurred on the server");
        }
    }

    @Override
    public Collection<User> findAll() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new InternalServerError("An unexpected error occurred on the server");
        }
    }

    @Override
    public Optional<User> findById(Long userId) {
        try {
            Optional<User> user = userRepository.findById(userId);

            if (user.isEmpty()) {
                throw new NotFoundException("User is not found");
            } else {
                return user;
            }
        } catch (Exception e) {
            throw new InternalServerError("An unexpected error occurred on the server");
        }
    }

    @Override
    public Optional<User> update(User user) {
        try {
            if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                throw new Conflict("This email is already exist");
            }

            return userRepository.update(user);
        } catch (Exception e) {
            throw new InternalServerError("An unexpected error occurred on the server");
        }
    }

    @Override
    public void delete(Long userId) {
        try {
            userRepository.delete(userId);
        } catch (Exception e) {
            throw new InternalServerError("An unexpected error occurred on the server");
        }
    }
}
