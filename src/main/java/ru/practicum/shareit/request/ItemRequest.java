package ru.practicum.shareit.request;

import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * TODO Sprint add-item-requests.
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemRequest {
    Long userId;
    @Size(max = 100, message = "Максимальная длина имени — 100 символов")
    String name;
    Long userRespondId;
}
