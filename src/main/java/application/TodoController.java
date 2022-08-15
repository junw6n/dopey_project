package application;

import domain.Todo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import domain.serviceFactory;
import domain.TodoService;

import java.util.ArrayList;

@Controller
public class TodoController {
    private TodoService service = new serviceFactory().todolistServiceFactroy();

    @GetMapping("/todolist")
    public String view(Model model) {
        ArrayList<Todo> list = service.allToDoList();
        model.addAttribute("list", list);
        return "todolist";
    }

    @PostMapping("/todolist/add")
    public String add(@RequestParam("title") String title,
                      @RequestParam("description") String description) {
        service.addTodo(title, description);
        return "redirect:/todolist";
    }

    @PostMapping("/todolist/remove")
    public String remove(@RequestParam("id") int id) {
        service.removeTodo(id);
        return "redirect:/todolist";
    }

    @PostMapping("/todolist/setStatus")
    public String setStatus(@RequestParam("id") int id,
                            @RequestParam("status") int status) {
        service.setStatus(id, status);
        return "redirect:/todolist";
    }
}
