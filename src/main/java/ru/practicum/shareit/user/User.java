package ru.practicum.shareit.user;

import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * TODO Sprint add-controllers.
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    Long id;
    @Pattern(regexp = "^\\S*$", message = "Логин не может содержать пробелы")
    String login;
}
