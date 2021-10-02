package by.it_academy.jd2.crm.service;

import by.it_academy.jd2.crm.model.Employer;
import by.it_academy.jd2.crm.service.api.IEmployersService;
import by.it_academy.jd2.crm.service.api.ISearchService;
import by.it_academy.jd2.crm.storage.EmployerHibStorage;
import by.it_academy.jd2.crm.storage.EmployerStorage;
import by.it_academy.jd2.crm.storage.api.IEmployerStorage;
import by.it_academy.jd2.crm.storage.api.ISearchStorage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class EmployersService implements IEmployersService, ISearchService {
    private static EmployersService instance;

    private final IEmployerStorage storage = EmployerHibStorage.getInstance();
//    private final IEmployerStorage storage = EmployerStorage.getInstance();

    private final ISearchStorage searchStorage = EmployerHibStorage.getInstance();

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
            employer.setName(names.get(random.nextInt(maxName)).trim());
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

    @Override
    public List<Employer> getAllEmployers() {
        return this.storage.getAllEmployers();
    }

    @Override
    public Employer getEmployer(long id) {
        return this.storage.getEmployer(id);
    }

    @Override
    public List<Employer> getEmployersOffLimit(int offset, int limit) {
        return this.storage.getEmployersOffLimit(offset, limit);
    }

    @Override
    public List<Employer> getEmployersSearch(int offset, int limit, String name, double from, double to) {
        return this.searchStorage.getEmployersSearch(offset, limit, name, from, to);
    }
}
