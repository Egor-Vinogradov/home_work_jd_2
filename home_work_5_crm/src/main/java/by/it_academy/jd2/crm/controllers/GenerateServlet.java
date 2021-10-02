package by.it_academy.jd2.crm.controllers;

import by.it_academy.jd2.crm.model.Department;
import by.it_academy.jd2.crm.service.EmployersService;
import by.it_academy.jd2.crm.service.PositionDepartmentService;
import by.it_academy.jd2.crm.service.api.IEmployersService;
import by.it_academy.jd2.crm.service.api.IPositionDepartmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "GenerateServlet", urlPatterns = "/generate")
public class GenerateServlet extends HttpServlet {

    private IEmployersService service = EmployersService.getInstance();
    private IPositionDepartmentService servicePD = PositionDepartmentService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("generate") != null) {
            this.service.generateEmployers(1000);
        } else if (req.getParameter("clean") != null) {
            this.service.deleteAll();
        }

        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
