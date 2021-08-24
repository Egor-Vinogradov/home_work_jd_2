package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.Person;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class DtoService {
    private final static DtoService instance = new DtoService();

    private static final String FIRST_NAME_PARAM_NAME = "firstName";
    private static final String LAST_NAME_PARAM_NAME = "lastName";
    private static final String AGE_NAME_PARAM_NAME = "age";

    private final Person person = new Person();

    public static DtoService getInstance() {
        return instance;
    }

    public Person getPerson() {
        return person;
    }

    public void useCookie(HttpServletRequest req, HttpServletResponse resp) {
        String firstName = getValueFromCookie(req, FIRST_NAME_PARAM_NAME);
        saveCookies(resp, FIRST_NAME_PARAM_NAME, firstName);

        String lastName = getValueFromCookie(req, LAST_NAME_PARAM_NAME);
        saveCookies(resp, LAST_NAME_PARAM_NAME, lastName);

        String stringAge = getValueFromCookie(req, AGE_NAME_PARAM_NAME);
        saveCookies(resp, AGE_NAME_PARAM_NAME, stringAge);

        if (firstName == null || lastName == null || stringAge == null) {
            throw new IllegalArgumentException("Не задан обязательный параметр");
        }

        int age = Integer.parseInt(stringAge);

        person.setLastName(lastName);
        person.setFirstName(firstName);
        person.setAge(age);

    }

    public void useSession(HttpServletRequest req, HttpServletResponse resp) {
        String firstName = getValueFromSession(req, FIRST_NAME_PARAM_NAME);
        saveSession(req, FIRST_NAME_PARAM_NAME, firstName);

        String lastName = getValueFromSession(req, LAST_NAME_PARAM_NAME);
        saveSession(req, LAST_NAME_PARAM_NAME, lastName);

        String stringAge = getValueFromSession(req, AGE_NAME_PARAM_NAME);
        saveSession(req, AGE_NAME_PARAM_NAME, stringAge);

        if (firstName == null || lastName == null || stringAge == null) {
            throw new IllegalArgumentException("Не задан обязательный параметр");
        }

        int age = Integer.parseInt(stringAge);

        person.setLastName(lastName);
        person.setFirstName(firstName);
        person.setAge(age);
    }

    public String getValueFromCookie(HttpServletRequest req,
                                     String key){
        String val = req.getParameter(key);

        if(val == null){
            Cookie[] cookies = req.getCookies();

            if(cookies != null) {
                val = Arrays.stream(cookies)
                        .filter(c -> key.equalsIgnoreCase(c.getName()))
                        .map(Cookie::getValue)
                        .findFirst()
                        .orElse(null);
            }
        }

        return val;
    }

    public void saveCookies(HttpServletResponse resp, String key, String val) {

        Cookie myCookie = new Cookie(key, URLEncoder.encode(val, StandardCharsets.UTF_8));
        myCookie.setMaxAge(Math.toIntExact(TimeUnit.DAYS.toSeconds(1)));
        resp.addCookie(myCookie);
    }

    public String getValueFromSession(HttpServletRequest req,
                                              String key) {
        String val = req.getParameter(key);

        if (val == null) {
            HttpSession session = req.getSession();

            if (!session.isNew()) {
                val = (String) session.getAttribute(key);
            }
        }

        return val;
    }

    public void saveSession(HttpServletRequest req,String key, String val) {
        HttpSession session = req.getSession();
        session.setAttribute(key, val);
    }
}
