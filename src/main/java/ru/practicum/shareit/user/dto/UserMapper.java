package ru.practicum.shareit.user.dto;

import ru.practicum.shareit.user.User;

public class UserMapper {
    public static UserDto toDtoUser(User user) {
        return UserDto.builder()
                .name(user.getName())
                .build();
    }
}
