package by.it_academy.jd2.crm.controllers;

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

@WebServlet(name = "DepartmentCardServlet", urlPatterns = "/departmentcard")
public class DepartmentCardServlet extends HttpServlet {

//    private IPositionDepartmentService service = PositionDepartmentService.getInstance();
    private IPositionDepartmentService service =
//        ApplicationContextUtil.getContext().getBean(PositionDepartmentService.class);
        ApplicationContextUtilAnnotation.getContext().getBean(PositionDepartmentService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idNumber = req.getParameter("id");
        long id = Long.parseLong(idNumber);

        req.setAttribute("department", this.service.getDepartment(id));

        req.getRequestDispatcher("/views/departmentcard.jsp").forward(req, resp);
    }
}
