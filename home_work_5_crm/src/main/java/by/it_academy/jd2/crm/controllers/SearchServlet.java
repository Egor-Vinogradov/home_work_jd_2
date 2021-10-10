package by.it_academy.jd2.crm.controllers;

import by.it_academy.jd2.crm.model.ConfigDB;
import by.it_academy.jd2.crm.model.Employer;
import by.it_academy.jd2.crm.model.filter.EmployeeSearchFilter;
import by.it_academy.jd2.crm.service.EmployersService;
import by.it_academy.jd2.crm.service.api.IEmployersService;
import by.it_academy.jd2.crm.service.api.ISearchService;
import by.it_academy.jd2.crm.service.spring.ApplicationContextUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchServlet", urlPatterns = "/search")
public class SearchServlet extends HttpServlet {


//    private IEmployersService service = EmployersService.getInstance();
    private IEmployersService service = ApplicationContextUtil.getContext().getBean(EmployersService.class);
//    private ISearchService searchService = EmployersService.getInstance();
    private ISearchService searchService = ApplicationContextUtil.getContext().getBean(EmployersService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String fromStr = req.getParameter("from");
        String toStr = req.getParameter("to");

        double from = fromStr != "" ? Double.parseDouble(fromStr) : Double.MIN_VALUE;
        double to = toStr != "" ? Double.parseDouble(toStr) : Double.MAX_VALUE;

        String offsetStr = req.getParameter("offset");
        int offset = 1;
        if (offsetStr != null) {
            offset = Integer.parseInt(offsetStr);
        }

        int firstButton = 1;
        if (offset > 3) {
            firstButton = offset - 2;
        }


        int limit = 5;
        int generalListSize = this.searchService.getEmployersSearch(0, Integer.MAX_VALUE, name, from, to).size();
        int numberPages = generalListSize / limit;
        int endButton = firstButton + 4;
        if (endButton >= numberPages) {
            endButton = numberPages;
        }

        String position = req.getParameter("position");
        if (position != null) {
            switch (position) {
                case "first":
                    firstButton = 1;
                    endButton = firstButton + 4;
                    break;
                case "last":
                    firstButton = generalListSize / limit - 2 < 1 ? 1 : generalListSize / limit - 2;
                    offset = generalListSize / limit;
                    endButton = generalListSize / limit;
                    break;
            }
        }

        offset = (offset - 1) * limit;

//        List<Employer> list = this.searchService.getEmployersSearch(offset, limit, name, from, to);
        EmployeeSearchFilter filter = new EmployeeSearchFilter(offset, limit, null,
                null, name, null, from, to, null);

        List<Employer> list = this.searchService.getEmployersSearch(filter);

        req.setAttribute("firstButton", firstButton);
        req.setAttribute("endButton", endButton);
        req.setAttribute("employers", list);

        req.setAttribute("name", name);
        req.setAttribute("from", from);
        req.setAttribute("to", to);

        req.getRequestDispatcher("/views/employerslimit.jsp").forward(req, resp);
    }
}
