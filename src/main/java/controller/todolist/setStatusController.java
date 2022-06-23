package controller.todolist;

import service.todolistService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/todolist/setStatus")
public class setStatusController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String status = request.getParameter("status");

        if (id == null || id.equals("")) return;
        if (status == null || status.equals("")) return;

        int id_ = Integer.parseInt(id);
        int status_ = Integer.parseInt(status);

        todolistService service = new todolistService();

        int result = service.setStatus(id_, status_);

        if (result == 1) response.sendRedirect("/todolist");
        else System.out.println("failed...");
    }
}
