package by.it_academy.jd2.crm.service;

import by.it_academy.jd2.crm.controllers.DBInitializer;
import by.it_academy.jd2.crm.model.Employer;
import by.it_academy.jd2.crm.service.api.IEmployersService;
import by.it_academy.jd2.crm.storage.EmployerStorage;
import by.it_academy.jd2.crm.storage.api.IEmployerStorage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class EmployersService implements IEmployersService {
    private static EmployersService instance;

    private final IEmployerStorage storage = EmployerStorage.getInstance();

    public static EmployersService getInstance() {
        if (instance == null) {
            synchronized (EmployersService.class) {
                instance = new EmployersService();
            }
        }
        return instance;
    }

    @Override
    public void add(Employer employer) {
        this.storage.addEmployer(employer);
    }

    @Override
    public void deleteAll() {
        this.storage.deleteAll();
    }

    @Override
    public void generateEmployers(int number) {
        Random random = new Random();
        List<String> names = getListNames();

        double maxSalary = 99999999.99;
        int maxName = names.size() - 1;

        for (int i = 0; i < number; i++) {
            double salary = random.nextDouble() * maxSalary;
            long numberPosition = ThreadLocalRandom.current().nextLong(3,11);
            long numberDepartment = ThreadLocalRandom.current().nextLong(3,6);

            Employer employer = new Employer();
            employer.setName(names.get(random.nextInt(maxName)));
            employer.setSalary(salary);
            employer.setPosition(numberPosition);
            employer.setDepartment(numberDepartment);

            add(employer);
        }
    }

    private List<String> getListNames() {
        List<String> list = new ArrayList<>();
        Path pathFile = Path.of("c:\\temp\\name.txt");
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

    @Override
    public int getCountEmployers() {
        return this.storage.getCountEmployers();
    }
}
