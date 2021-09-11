package by.it_academy.jd2.crm.controllers;

import by.it_academy.jd2.crm.model.ConfigDB;
import by.it_academy.jd2.crm.model.Employer;
import by.it_academy.jd2.crm.service.EmployersService;
import by.it_academy.jd2.crm.service.api.IEmployersService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StartServlet", urlPatterns = "/start")
public class StartServlet extends HttpServlet {


    private IEmployersService service = EmployersService.getInstance();

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("countEmployers", this.service.getCountEmployers());
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("countEmployers", this.service.getCountEmployers());
        req.getRequestDispatcher("index.jsp").forward(req, resp);

//        Employer employer = mapper.readValue(req.getInputStream(), Employer.class);
//
//        System.out.println(employer);
    }
}
