package by.it_academy.jd2.crm.service;

import by.it_academy.jd2.crm.model.Employer;
import by.it_academy.jd2.crm.service.api.IEmployersService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ValueGenerator {

    private static IEmployersService service = EmployersService.getInstance();

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        setEmployers(getListNames());
        long stopTime = System.currentTimeMillis();
        System.out.println("Потрачено: " + (stopTime - startTime) + " мс");
        deleteAll();
        long startTime2 = System.currentTimeMillis();
        setEmployers(getListNames());
        long stopTime2 = System.currentTimeMillis();
        System.out.println("Потрачено: " + (stopTime2 - startTime2) + " мс");
    }

    private static List<String> getListNames() {
        List<String> list = new ArrayList<>();
        Path pathFile = Path.of("name.txt");
        String result = "";

        try {
            result = Files.readString(pathFile);
        } catch (IOException e) {
            System.out.println("Ошибка с файлом");;
        }

        String[] arr = result.split("\\n");

        for (String s : arr) {
            list.add(s);
        }

        return list;
    }

    private static void setEmployers(List<String> names) {
        Random random = new Random();


        double maxSalary = 99999999.99;
        int maxName = names.size() - 1;

        for (int i = 0; i < 5; i++) {
            double salary = random.nextDouble() * maxSalary;
            long numberPosition = ThreadLocalRandom.current().nextLong(3,11);
            long numberDepartment = ThreadLocalRandom.current().nextLong(3,6);

            Employer employer = new Employer();
            employer.setName(names.get(random.nextInt(maxName)));
            employer.setSalary(salary);
            employer.setPosition(numberPosition);
            employer.setDepartment(numberDepartment);

            service.add(employer);
        }
    }

    private static void deleteAll() {
        service.deleteAll();
    }
}
