package ru.practicum.shareit.request;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;

/**
 * TODO Sprint add-item-requests.
 */
@Entity
@Table(
        name = "itemRequests",
        schema = "public",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "name"})
        }
)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
public class ItemRequest {

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
    @NotBlank(message = "Название запрашиваемой вещи не может быть пусто")
    @Size(max = 100, message = "Максимальная длина имени — 100 символов")
    String name;

    @Column(name = "user_respond_id")
    Long userRespondId;

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
