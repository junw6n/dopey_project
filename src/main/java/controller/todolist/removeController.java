package controller.todolist;

import service.serviceFactory;
import service.todolistService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/todolist/remove")
public class removeController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null || id.equals("")) return;

        int id_ = Integer.parseInt(id);

        todolistService service = new serviceFactory().todolistServiceFactroy();

        int result = service.removeTodo(id_);

        if (result == 1) response.sendRedirect("/todolist");
        else System.out.println("failed...");
    }
}
