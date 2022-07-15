package controller.todolist;

import service.serviceFactory;
import service.todolistService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/todolist/add")
public class addController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        todolistService service = new serviceFactory().todolistServiceFactroy();

        int result = service.addTodo(title, description);

        if (result == 1) response.sendRedirect("/todolist");
        else System.out.println("failed...");
    }
}
