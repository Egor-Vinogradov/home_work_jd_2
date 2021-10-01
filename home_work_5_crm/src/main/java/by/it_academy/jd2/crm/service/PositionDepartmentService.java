package by.it_academy.jd2.crm.service;

import by.it_academy.jd2.crm.model.Department;
import by.it_academy.jd2.crm.model.Position;
import by.it_academy.jd2.crm.service.api.IPositionDepartmentService;
import by.it_academy.jd2.crm.storage.PosDepHibStorage;
import by.it_academy.jd2.crm.storage.PositionDepartmentStorage;
import by.it_academy.jd2.crm.storage.api.IPositionDepartmentStorage;

import java.util.List;

public class PositionDepartmentService implements IPositionDepartmentService {
    private static PositionDepartmentService instance;

//    private final IPositionDepartmentStorage storage = PositionDepartmentStorage.getInstance();
    private final IPositionDepartmentStorage storage = PosDepHibStorage.getInstance();

    public static PositionDepartmentService getInstance() {
        if (instance == null) {
            synchronized (PositionDepartmentService.class) {
                instance = new PositionDepartmentService();
            }
        }
        return instance;
    }

    @Override
    public int getCount(String value) {
        return this.storage.getCount(value);
    }

    @Override
    public List<Department> getAllDepartments() {
        return this.storage.getAllDepartments();
    }

    @Override
    public String getNameDepartment(long id) {
        return this.storage.getNameDepartment(id);
    }

    @Override
    public List<Position> getAllPositions() {
        return this.storage.getAllPositions();
    }

    @Override
    public Position getPosition(long id) {
        return this.storage.getPosition(id);
    }

    @Override
    public Department getDepartment(long id) {
        return this.storage.getDepartment(id);
    }
}
