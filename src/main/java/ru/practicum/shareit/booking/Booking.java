package ru.practicum.shareit.booking;

import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

/**
 * TODO Sprint add-bookings.
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Booking {
    Long itemId;
    Long userId;
    Set<LocalDate> days;
    @Size(max = 600, message = "Максимальная длина отзыва — 600 символов")
    String review;
}
