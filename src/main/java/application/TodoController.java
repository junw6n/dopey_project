package application;

import domain.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import domain.TodoService;

import java.util.List;

@Controller
@RequestMapping("/todolist")
public class TodoController {
    @Autowired private TodoService service;

    @GetMapping()
    public String view(Model model) {
        List<Todo> list = service.getAll();
        model.addAttribute("list", list);
        return "todolist";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Todo todo) {
        service.add(todo);
        return "redirect:/todolist";
    }

    @PostMapping("/remove")
    public String remove(@ModelAttribute Todo todo) {
        service.remove(todo);
        return "redirect:/todolist";
    }

    @PostMapping("/setStatus")
    public String setStatus(@ModelAttribute Todo todo) {
        service.setStatus(todo);
        return "redirect:/todolist";
    }

    public void setService(TodoService service) {
        this.service = service;
    }
}
