package com.thewhite.demo.model;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**
     * ID пользователя
     */
    Long id;

    /**
     * Имя
     */
    String name;

    /**
     * Роль в системе
     */
    UserRole role;

    public boolean isChief() {
        return role == UserRole.CHIEF;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
