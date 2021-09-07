package by.it_academy.jd2.crm.controllers;

import by.it_academy.jd2.crm.model.DBConfig;
import by.it_academy.jd2.crm.model.Employer;
import by.it_academy.jd2.crm.service.EmployersService;
import by.it_academy.jd2.crm.service.api.IEmployersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "StartServlet", urlPatterns = "/")
public class StartServlet extends HttpServlet {
    private static final String USER_MAME = "postgres";
    private static final String PASSWORD = "egor";
    private static final String URL = "jdbc:postgresql://localhost:5432/crm";

    private IEmployersService service = EmployersService.getInstance();

    @Override
    public void init(ServletConfig config) throws ServletException {
        DBInitializer dbInitializer = DBInitializer.getInstance();

        DBConfig dbConfig = new DBConfig();
        dbConfig.setUrl(URL);
        dbConfig.setUser(USER_MAME);
        dbConfig.setPassword(PASSWORD);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String salaryStr = req.getParameter("salary");

        Employer employer = new Employer();
        employer.setName(name);
        employer.setSalary(Double.parseDouble(salaryStr));

        service.add(employer);


    }
}
