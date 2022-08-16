package service;

import domain.Todo;
import domain.TodoService;
import domain.TodoStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "file:web/WEB-INF/dispatcher-servlet.xml")
class TodoServiceTest {
    @Autowired
    private TodoService service;
    private Todo todo1;
    private Todo todo2;
    private Todo todo3;
    private final int initCount = 4;

    boolean sameTodo(Todo todo1, Todo todo2) {
        return todo1.getTitle().equals(todo2.getTitle()) && todo1.getDescription().equals(todo2.getDescription());
    }

    @BeforeEach
    void setUp() {
        this.todo1 = new Todo(1, "work out - 22.06.21", "legs", TodoStatus.IDLE);
        this.todo2 = new Todo(2, "work out - 22.06.23", "chest, arms", TodoStatus.IDLE);
        this.todo3 = new Todo(3, "read book 2", "\"object-oriented programming\"", TodoStatus.IDLE);
    }
    @Test
    void allToDoList() {
        assertEquals(initCount, service.getAll().size());

        List<Todo> test = new ArrayList<>();
        test.add(this.todo1);
        test.add(this.todo2);

        // add
        test.forEach(todo -> {
            service.add(todo);
        });

        List<Todo> list = service.getAll();

        assertEquals(initCount + 2, list.size());
        assertTrue(sameTodo(this.todo1, list.get(list.size()-2)));
        assertTrue(sameTodo(this.todo2, list.get(list.size()-1)));

        // remove
        list.subList(list.size() - 2, list.size()).forEach(todo -> {
            service.remove(todo);
        });

        assertEquals(initCount, service.getAll().size());
    }

    @Test
    void addTodo() {

    }

    @Test
    void removeTodo() {
    }

    @Test
    void setStatus() {
    }
}