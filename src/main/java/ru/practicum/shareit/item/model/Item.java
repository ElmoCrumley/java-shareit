package ru.practicum.shareit.item.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.user.User;

/**
 * TODO Sprint add-controllers.
 */
@Entity
@Table(
        name = "items",
        schema = "public",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "name"})
        }
)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id")
    User user;

    @Column(
            name = "name",
            nullable = false,
            length = 100)
    @NotBlank(message = "Название вещи не может быть пусто")
    @Size(max = 100, message = "Максимальная длина имени - 100 символов")
    String name;

    @Column(
            name = "description",
            length = 600)
    @Size(max = 600, message = "Максимальная длина описания - 600 символов")
    String description;

    @Column(name = "available")
    Boolean available;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        return id != null && id.equals(((Item) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
