package by.it_academy.jd2;

import by.it_academy.jd2.dto.Person;
import by.it_academy.jd2.service.DtoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DtoServlet", urlPatterns = "/")
public class DtoServlet extends HttpServlet {


    /**
     * Переменная для выбора места хранения данных (cookie либо session)
     */
    private static final String STORAGE_PARAM_NAME = "storage";

    /**
     * Переменные хранения необходимых синглтонов
     */
    private static final DtoService serviceDto = DtoService.getInstance();
    private static final Person person = serviceDto.getPerson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * Переадресация запроса GET на POS
         */
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /**
         * получаем место хранения данных через заголовок
         */
        String storage = req.getHeader(STORAGE_PARAM_NAME);

        /**
         * проверяем на наличие даннх в заголовке
         */
        if (storage == null) {
            return;
        }

        /**
         * В зависимости от переданного параметра выбираем место хранения
         * и вызываем соответствующий метод
         */
        switch (storage) {
            case "cookie":
                serviceDto.useCookie(req, resp);
                break;
            case "session":
                serviceDto.useSession(req, resp);
                break;
            default:
                break;
        }

        /**
         * Инициализируем writer и печатем полученое значение на странице
         */
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        writer.write(person.getLastName() + " " + person.getFirstName() + " " + person.getAge());
    }
}
