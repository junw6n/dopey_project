package controller.todolist;

import entity.Todo;
import service.todolistService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet("/todolist")
public class viewController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        todolistService service = new todolistService();
        ArrayList<Todo> list = service.allToDoList();

        request.setAttribute("list", list);
        request.getRequestDispatcher("/WEB-INF/view/todolist.jsp").forward(request, response);
    }
}
