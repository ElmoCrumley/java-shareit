package ru.practicum.shareit.item.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * TODO Sprint add-controllers.
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Item {
    Long id;
    Long userId;
    @NotBlank(message = "Поле должно быть заполнено")
    @Size(max = 100, message = "Максимальная длина имени — 100 символов")
    String name;
    @Size(max = 600, message = "Максимальная длина описания — 600 символов")
    String description;
    Boolean available;
}
