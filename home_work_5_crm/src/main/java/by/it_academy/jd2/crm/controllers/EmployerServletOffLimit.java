package by.it_academy.jd2.crm.controllers;

import by.it_academy.jd2.crm.model.Employer;
import by.it_academy.jd2.crm.service.EmployersService;
import by.it_academy.jd2.crm.service.api.IEmployersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmployerServletOffLimit", urlPatterns = "/employerslimit")
public class EmployerServletOffLimit extends HttpServlet {

    private IEmployersService service = EmployersService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String offsetStr = req.getParameter("offset");
        int offset = 1;
        if (offsetStr != null) {
            offset = Integer.parseInt(offsetStr);
        }

        String numberButtonStr = req.getParameter("nb");
        int numberButton = 1;
        if (numberButtonStr != null) {
            numberButton = Integer.parseInt(numberButtonStr);
        }

        int firstButton = 1;
        if (numberButton > 3) {
            firstButton = numberButton - 2;
        }

        int generalListSize = this.service.getCountEmployers();
        int limit = 5;
        int numberPages = generalListSize / limit;
        int endButton = firstButton + 4;
        if (endButton >= numberPages) {
            endButton = numberPages;
        }

        offset = (numberButton - 1) * limit;

        List<Employer> list = this.service.getEmployersOffLimit(offset, limit);

        req.setAttribute("firstButton", firstButton);
        req.setAttribute("endButton", endButton);
        req.setAttribute("employers", list);

        req.getRequestDispatcher("/views/employerslimit.jsp").forward(req, resp);
    }
}
