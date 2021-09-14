package by.it_academy.jd2.crm.controllers;

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

@WebServlet(name = "EmployersCardServlet", urlPatterns = "/employercard")
public class EmployersCardServlet extends HttpServlet {

    private IEmployersService service = EmployersService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idNumber = req.getParameter("id");
        long id = Long.parseLong(idNumber);

        req.setAttribute("employer", this.service.getEmployer(id));

        req.getRequestDispatcher("/views/employercard.jsp").forward(req, resp);
    }
}
