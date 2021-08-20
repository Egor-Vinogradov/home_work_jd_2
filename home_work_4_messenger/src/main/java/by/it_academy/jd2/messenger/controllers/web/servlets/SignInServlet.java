package by.it_academy.jd2.messenger.controllers.web.servlets;

import by.it_academy.jd2.messenger.model.User;
import by.it_academy.jd2.messenger.view.AuthService;
import by.it_academy.jd2.messenger.view.api.IAuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SignInServlet", urlPatterns = "/signIn")
public class SignInServlet extends HttpServlet {

    /**
     * Переменная для инициализации класса для авторизации
     */
    private final IAuthService authService;

    public SignInServlet() {
        /**
         * инициализируем авторизацию
         */
        this.authService = AuthService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * При нажатии в index.jsp кнопки вход перенаправляем на страницу авторизации
         */
        req.getRequestDispatcher("/views/signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * Переменные для получения из переданного параметра логина и пароля
         */
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        // получаем юзера по логину и паролю
        User user = authService.auth(login, password);

        /**
         * Если юзер не получен, тогда передаем сообщение об ошибке в jsp и обновляем страницу
         * Если получен, тогда передаем юзера в jsp и переходим на главную страницу
         * Если юзер == админ, тогда идем в админку
         */
        if(user == null){
            req.setAttribute("error", true);
            req.setAttribute("message", "Что-то пошло не так!!!");
            req.getRequestDispatcher("/views/signIn.jsp").forward(req, resp);
        } else {
            if ((user.getLogin()).equals("admin")) {
                req.getSession().setAttribute("user", user);
                resp.sendRedirect(req.getContextPath() + "/admin");
            } else {
                req.getSession().setAttribute("user", user);
                resp.sendRedirect(req.getContextPath() + "/");
            }
        }
    }
}
