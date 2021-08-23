package by.it_academy.jd2.messenger.controllers.web.servlets;

import by.it_academy.jd2.messenger.view.SavingRestoringDataFile;
import by.it_academy.jd2.messenger.view.api.ISavingRestoringData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Сервлет для выхода пользователя
 */
@WebServlet(name = "LogOutServlet", urlPatterns = "/logOut")
public class LogOutServlet extends HttpServlet {

    /**
     * Метод проверяет наличие сессии. Если она есть, тогда завершает и перенаправляет на старт страницу
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
