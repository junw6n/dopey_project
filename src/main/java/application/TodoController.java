package application;

import domain.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import domain.serviceFactory;
import domain.TodoService;

import java.util.ArrayList;

@Controller
@RequestMapping("/todolist")
public class TodoController {
    private final TodoService service;

    @Autowired
    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping()
    public String view(Model model) {
        ArrayList<Todo> list = service.allToDoList();
        model.addAttribute("list", list);
        return "todolist";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute TodoAddRequestDTO dto) {
        service.addTodo(dto.getTitle(), dto.getDescription());
        return "redirect:/todolist";
    }

    @PostMapping("/remove")
    public String remove(@ModelAttribute TodoRemoveRequestDTO dto) {
        service.removeTodo(dto.getId());
        return "redirect:/todolist";
    }

    @PostMapping("/setStatus")
    public String setStatus(@ModelAttribute TodoSetStatusRequestDTO dto) {
        service.setStatus(dto.getId(), dto.getStatus());
        return "redirect:/todolist";
    }
}
