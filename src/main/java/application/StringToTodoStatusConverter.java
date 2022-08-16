package application;

import domain.TodoStatus;
import org.springframework.core.convert.converter.Converter;

public class StringToTodoStatusConverter implements Converter<String, TodoStatus> {
    public TodoStatus convert(String source) {
        return TodoStatus.valueOf(Integer.parseInt(source));
    }
}
