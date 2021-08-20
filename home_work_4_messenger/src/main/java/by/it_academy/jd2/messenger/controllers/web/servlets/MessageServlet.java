package by.it_academy.jd2.messenger.controllers.web.servlets;

import by.it_academy.jd2.messenger.model.Message;
import by.it_academy.jd2.messenger.model.User;
import by.it_academy.jd2.messenger.view.MessageService;
import by.it_academy.jd2.messenger.view.api.IMessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "MessageServlet", urlPatterns = "/message")
public class MessageServlet extends HttpServlet {

    private final IMessageService messageService;

    public MessageServlet() {
        this.messageService = MessageService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/message.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");

        if(user == null){
            throw new SecurityException("Пользователь не задан");
        }

        String recipient = req.getParameter("recipient");
        String text = req.getParameter("text");

        /**
         * Создание сообщения
         */
        Message message = new Message();
        message.setFrom(user.getLogin());
        message.setSendDate(new Date());
        message.setText(text);

        try{
            this.messageService.addMessage(recipient, message);
            // делаем запись, что сообщение отправлено
            req.setAttribute("success", true);
        } catch (IllegalArgumentException e){
            // указываем, что ошибка и пишем причину
            req.setAttribute("error", true);
            req.setAttribute("message",  e.getMessage());
        }
        // переходим на страницу сообщений обратно
        req.getRequestDispatcher("/views/message.jsp").forward(req, resp);
    }
}
