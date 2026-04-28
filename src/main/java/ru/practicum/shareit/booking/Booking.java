package ru.practicum.shareit.booking;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;

import java.time.LocalDate;
import java.util.Set;

/**
 * TODO Sprint add-bookings.
 */
@Entity
@Table(
        name = "bookings",
        schema = "public",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "item_id"})
        })
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
public class Booking {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "item_id",
            referencedColumnName = "id")
    Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id")
    User user;

    @ElementCollection(
            targetClass = LocalDate.class,
            fetch = FetchType.EAGER)
    @CollectionTable(
            name = "days",
            joinColumns = @JoinColumn(name = "booking_id"))
    @Column(
            name = "day",
            nullable = false)
    Set<LocalDate> days;

    @Column(
            name = "review",
            length = 600)
    @Size(max = 600, message = "Максимальная длина отзыва — 600 символов")
    String review;
}
