package application;

import domain.TodoStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
//        this.mockMvc = MockMvcBuilders.standaloneSetup(new TodoController()).build();
    }

    @Test
    public void todolist_view() throws Exception {
        this.mockMvc.perform(get("/todolist"))
                .andExpect(view().name("todolist"));
    }

    @Test
    public void todo_add() throws Exception {
        this.mockMvc.perform(post("/todolist/add")
                .param("title", "title-test")
                .param("description", "desct-test"))
                .andExpect(redirectedUrl("/todolist"));
    }

    @Test
    public void todo_remove() throws Exception {
        this.mockMvc.perform(post("/todolist/remove")
                .param("id", "1"))
                .andExpect(redirectedUrl("/todolist"));
    }

    @Test
    public void change_todo_status() throws Exception {
        this.mockMvc.perform(post("/todolist/setStatus")
                .param("id", "1")
                .param("status", String.valueOf(TodoStatus.IN_PROGRESS.intValue())))
                .andExpect(redirectedUrl("/todolist"));
    }
}
