package by.it_academy.jd2.crm.controllers;

import by.it_academy.jd2.crm.service.PositionDepartmentService;
import by.it_academy.jd2.crm.service.api.IPositionDepartmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PositionCardServlet", urlPatterns = "/positioncard")
public class PositionCardServlet extends HttpServlet {

    private IPositionDepartmentService service = PositionDepartmentService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idNumber = req.getParameter("id");
        long id = Long.parseLong(idNumber);

        req.setAttribute("position", this.service.getPosition(id));

        req.getRequestDispatcher("/views/positioncard.jsp").forward(req, resp);
    }
}
