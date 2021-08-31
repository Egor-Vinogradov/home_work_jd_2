package by.it_academy.jd2.messenger.controllers.web.servlets;

import by.it_academy.jd2.messenger.model.User;
import by.it_academy.jd2.messenger.view.service.UserService;
import by.it_academy.jd2.messenger.view.api.IUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "SignUpServlet", urlPatterns = "/signUp")
public class SignUpServlet extends HttpServlet {

    /**
     * Переменная для инициализации класса для сохранения пользователя (сервиса)
     */
    private final IUserService userService;

    public SignUpServlet() {
        /**
         * Инициализация сервиса пользователя
         */
        this.userService = UserService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * При нажатии в index.jsp кнопки вход перенаправляем на страницу авторизации
         */
        req.getRequestDispatcher("/views/signUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * Переменные для записи параметров
         */
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        String fio = req.getParameter("fio");
        String date = req.getParameter("date");

        /**
         * Код для преобразования даты из формы
         */
        SimpleDateFormat availDate = new SimpleDateFormat("yyyy-MM-dd");
        Date chosenDate = null;
        try {
            chosenDate = availDate.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        /**
         * Создаем пользователя
         */
        User user = new User();
        user.setLogin(login);
        user.setPassword(pass);
        user.setFio(fio);
        user.setBirthday(chosenDate);
        user.setRegistration(new Date());

        /**
         * Проверка заполнения данных пользователя и сохранение
         * Если прилетает эксепшн при незаполненом поле, то выдаем в jsp ошибку
         */
        try{
            this.userService.signUp(user);
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/");
        } catch (IllegalArgumentException e){
            req.setAttribute("error", true);
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("/views/signUp.jsp").forward(req, resp);
        }
    }
}
