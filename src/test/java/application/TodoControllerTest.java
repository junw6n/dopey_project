package application;

import domain.Todo;
import domain.TodoService;
import domain.TodoStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "file:web/WEB-INF/dispatcher-servlet.xml")
public class TodoControllerTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    private TodoService service;

    private Todo sampleTodo;

    @Before
    @DirtiesContext
    public void setup() {
        service = mock(TodoService.class);
        TodoController todoController = (TodoController) context.getBean("todoController");
        todoController.setService(service);

        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

//        sampleTodo = new Todo(1, "title: test", "desc: test", TodoStatus.IDLE);
        sampleTodo = mock(Todo.class);
    }

    @Test
    public void view_todo_list() throws Exception {
        mockMvc.perform(get("/todolist"))
                .andExpect(status().isOk())
                .andExpect(view().name("todolist"));
    }

    @Test
    public void add_todo() throws Exception {
        mockMvc.perform(post("/todolist/add")
                .param("title", sampleTodo.getTitle())
                .param("description", sampleTodo.getDescription()))
                .andExpect(redirectedUrl("/todolist"));
    }

    @Test
    public void remove_todo() throws Exception {
        mockMvc.perform(post("/todolist/remove")
                .param("id", String.valueOf(sampleTodo.getId())))
                .andExpect(redirectedUrl("/todolist"));
    }

    @Test
    public void change_todo_status() throws Exception {
        mockMvc.perform(post("/todolist/setStatus")
                .param("id", String.valueOf(sampleTodo.getId()))
                .param("status", String.valueOf(TodoStatus.IN_PROGRESS.intValue())))
                .andExpect(redirectedUrl("/todolist"));
    }
}
