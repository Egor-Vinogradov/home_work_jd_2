package by.it_academy.jd2.crm.controllers;

import by.it_academy.jd2.crm.model.Department;
import by.it_academy.jd2.crm.model.Position;
import by.it_academy.jd2.crm.service.PositionDepartmentService;
import by.it_academy.jd2.crm.service.api.IPositionDepartmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PositionServlet", urlPatterns = "/position")
public class PositionServlet extends HttpServlet {

    private IPositionDepartmentService service = PositionDepartmentService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Position> list = this.service.getAllPositions();

        req.setAttribute("positions", list);

        req.getRequestDispatcher("/views/positions.jsp").forward(req, resp);
    }
}
