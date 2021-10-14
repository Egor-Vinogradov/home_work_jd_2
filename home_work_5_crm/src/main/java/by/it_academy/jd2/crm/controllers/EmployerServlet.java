package by.it_academy.jd2.crm.controllers;

import by.it_academy.jd2.crm.model.Employer;
import by.it_academy.jd2.crm.service.EmployersService;
import by.it_academy.jd2.crm.service.api.IEmployersService;
import by.it_academy.jd2.crm.service.spring.ApplicationContextUtilAnnotation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmployerServlet", urlPatterns = "/employer")
public class EmployerServlet extends HttpServlet {

//    private IEmployersService service = EmployersService.getInstance();
    private IEmployersService service =
//        ApplicationContextUtil.getContext().getBean(EmployersService.class);
        ApplicationContextUtilAnnotation.getContext().getBean(EmployersService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employer> list = this.service.getAllEmployers();

        req.setAttribute("employers", list);

        req.getRequestDispatcher("/views/employers.jsp").forward(req, resp);
    }
}
