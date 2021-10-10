package by.it_academy.jd2.crm.service.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextUtil {

    private static ClassPathXmlApplicationContext context;

    static {
        context = new ClassPathXmlApplicationContext("service.xml");
    }

    public static ClassPathXmlApplicationContext getContext() {
        return context;
    }

    public static void close() {
        context.close();
    }
}
