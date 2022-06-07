package com.thewhite.demo.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    /**
     * ID задачи
     */
    Long id;

    /**
     * Название
     */
    String title;

    /**
     * Содержание
     */
    String content;

    /**
     * Важность
     */
    boolean urgency;

    /**
     * Срок выполнения
     */
    LocalDateTime deadline;

    /**
     * Исполнитель задачи
     */
    User executor;

    /**
     * Статус
     */
    TaskStatus status;

    /**
     * Чек-лист (условие, выполнено / не выполнено)
     */
    Map<String, Boolean> checkList = new HashMap<>();

    public boolean isCheckListComplete() {
        return checkList.isEmpty() || checkList.values()
                                               .stream()
                                               .allMatch(check -> check);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id.equals(task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
