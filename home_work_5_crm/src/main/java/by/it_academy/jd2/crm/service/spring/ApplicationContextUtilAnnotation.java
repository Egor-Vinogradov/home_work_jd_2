package by.it_academy.jd2.crm.service.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextUtilAnnotation {

    private static AnnotationConfigApplicationContext context;

    static {
        context = new AnnotationConfigApplicationContext("by.it_academy.jd2.crm.service.spring");
    }

    public static AnnotationConfigApplicationContext getContext() {
        /**
         * По логике это должен быть синглтон. Но через статик не работает
         */
//        return new AnnotationConfigApplicationContext("by.it_academy.jd2.crm.service.spring");
        return context;
    }

    public static void close() {
        context.close();
    }
}
