package by.it_academy.jd2.messenger.controllers.web.servlets.admin;

import by.it_academy.jd2.messenger.model.Message;
import by.it_academy.jd2.messenger.model.User;
import by.it_academy.jd2.messenger.view.service.MessageService;
import by.it_academy.jd2.messenger.view.service.UserService;
import by.it_academy.jd2.messenger.view.api.IMessageService;
import by.it_academy.jd2.messenger.view.api.IUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;

@WebServlet(name = "AdminServletUser", urlPatterns = "/admin/user")
public class AdminServletUser extends HttpServlet {

    /**
     * Переменная для инициализации сервиса
     */
    private final IUserService userService;
    private final IMessageService messageService;

    /**
     * Инициализация сервиса
     */
    public AdminServletUser() {
        this.userService = UserService.getInstance();
        this.messageService = MessageService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * Вывод списка юзеров, зарегестрированных в системе
         */
        PrintWriter writer = resp.getWriter();
        resp.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        resp.setContentType("text/html; charset=UTF-8");
        Collection<User> users = userService.getAll();
        for (User user : users) {
            writer.println("<p>" + user + "</p>");
            List<Message> list = this.messageService.get(user.getLogin());
            for (Message message : list) {
                writer.write("<p>" + message + "</p>");
            }
        }
    }
}
