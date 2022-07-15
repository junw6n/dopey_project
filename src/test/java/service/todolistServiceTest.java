package service;

import dbconnector.dbConnection;
import dbconnector.h2Connection;
import entity.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class todolistServiceTest {
    private todolistService service;
    private Todo todo1;
    private Todo todo2;
    private Todo todo3;
    private final int initCount = 4;

    boolean sameTodo(Todo todo1, Todo todo2) {
        return todo1.getTitle().equals(todo2.getTitle()) && todo1.getDescription().equals(todo2.getDescription());
    }

    @BeforeEach
    void setUp() {
        dbConnection dbconn = new h2Connection();
        this.service = new todolistService(dbconn);
        this.todo1 = new Todo(1, "work out - 22.06.21", "legs", 0);
        this.todo2 = new Todo(2, "work out - 22.06.23", "chest, arms", 0);
        this.todo3 = new Todo(3, "read book 2", "\"object-oriented programming\"", 0);
    }
    @Test
    void allToDoList() {
        assertEquals(initCount, service.allToDoList().size());

        ArrayList<Todo> test = new ArrayList<>();
        test.add(this.todo1);
        test.add(this.todo2);

        // add
        test.forEach(todo -> {
            service.addTodo(todo.getTitle(), todo.getDescription());
        });

        ArrayList<Todo> list = service.allToDoList();

        assertEquals(initCount + 2, list.size());
        assertTrue(sameTodo(this.todo1, list.get(list.size()-2)));
        assertTrue(sameTodo(this.todo2, list.get(list.size()-1)));

        // remove
        list.subList(list.size() - 2, list.size()).forEach(todo -> {
            service.removeTodo(todo.getId());
        });

        assertEquals(initCount, service.allToDoList().size());
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