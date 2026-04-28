package ru.practicum.shareit.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.item.model.Item;

/**
 * TODO Sprint add-controllers.
 */
@Entity
@Table(
        name = "users",
        schema = "public"
)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(
            name = "login",
            nullable = false,
            unique = true)
    @NotBlank(message = "Логин не может быть пустым")
    @Pattern(regexp = "^\\S*$", message = "Логин не может содержать пробелы")
    String login;

    @Column(
            name = "email",
            nullable = false,
            unique = true)
    @NotBlank(message = "Почта не может быть пустой")
    @Email
    String email;

    @Column(
            name = "name",
            length = 100)
    @Size(max = 100, message = "Максимальная длина имени пользователя — 100 символов")
    String name;

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
