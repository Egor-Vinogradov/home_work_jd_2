package by.it_academy.jd2.messenger.controllers.web.servlets.admin;


import by.it_academy.jd2.messenger.storage.AboutStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "AdminServletAbout", urlPatterns = "/admin/about")
public class AdminServletAbout extends HttpServlet {

    private final AboutStorage aboutStorage;

    public AdminServletAbout() {
        this.aboutStorage = AboutStorage.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        resp.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        resp.setContentType("text/html; charset=UTF-8");
        writer.write("<p>" + "Time of creation: " + aboutStorage.getAbout().getTimeСreation() + "</p>");
        writer.write("<p>" + "Storage: " + aboutStorage.getAbout().getStorage() + "</p>");
    }
}
