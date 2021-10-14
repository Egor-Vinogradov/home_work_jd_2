package by.it_academy.jd2.crm.controllers;

import by.it_academy.jd2.crm.model.Department;
import by.it_academy.jd2.crm.service.PositionDepartmentService;
import by.it_academy.jd2.crm.service.api.IPositionDepartmentService;
import by.it_academy.jd2.crm.service.spring.ApplicationContextUtil;
import by.it_academy.jd2.crm.service.spring.ApplicationContextUtilAnnotation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DepartmentServlet", urlPatterns = "/department")
public class DepartmentServlet extends HttpServlet {

//    private IPositionDepartmentService service = PositionDepartmentService.getInstance();
    private IPositionDepartmentService service =
//        ApplicationContextUtil.getContext().getBean(PositionDepartmentService.class);
        ApplicationContextUtilAnnotation.getContext().getBean(IPositionDepartmentService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Department> list = this.service.getAllDepartments();

        req.setAttribute("departments", list);

        req.getRequestDispatcher("/views/departments.jsp").forward(req, resp);
    }
}
