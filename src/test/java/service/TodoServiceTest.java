package service;

import domain.Todo;
import domain.TodoService;
import domain.TodoStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "file:web/WEB-INF/dispatcher-servlet.xml")
@Transactional
public class TodoServiceTest {
    @Autowired
    private TodoService service;

    private Todo sample;
    private final int initCount = 6;

    @Before
    public void setup() {
        sample = new Todo(999, "test", "This is a test todo!", TodoStatus.IDLE);
    }

    @Test
    public void get_exist_todo() {
        service.add(sample);

        Todo result = service.get(sample.getId());
        assertTrue(compare(sample, result));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void get_todo_not_exist() {
        service.get(786);
    }

    @Test(expected = DuplicateKeyException.class)
    public void add_todo_with_duplicated_id() {
        service.add(sample);
        service.add(sample);
    }

    @Test
    public void add_todo_with_null_id() {
        sample.setId(null);
        service.add(sample);

        assertEquals(initCount+1, service.getCount());
    }

    @Test
    public void remove_todo() {
        service.add(sample);
        int result = service.remove(sample);

        assertEquals(1, result);
    }

    @Test
    public void remove_not_exist_todo() {
        int result = service.remove(sample);

        assertEquals(0, result);
    }

    @Test
    public void change_todo_status() {
        service.add(sample);

        sample.setStatus(TodoStatus.IN_PROGRESS);
        service.setStatus(sample);
        assertEquals(TodoStatus.IN_PROGRESS, service.get(sample.getId()).getStatus());

        sample.setStatus(TodoStatus.COMPLETE);
        service.setStatus(sample);
        assertEquals(TodoStatus.COMPLETE, service.get(sample.getId()).getStatus());
    }

    @Test
    public void change_not_exist_todo() {
        sample.setStatus(TodoStatus.IN_PROGRESS);
        int result = service.setStatus(sample);

        assertEquals(0, result);
    }

    // issue: if there are todos have same title and description but different id.
    boolean compare(Todo todo1, Todo todo2) {
        boolean title = todo1.getTitle().equals(todo2.getTitle());
        boolean description = todo1.getDescription().equals(todo2.getDescription());

        return title && description;
    }
}