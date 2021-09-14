package by.it_academy.jd2.crm.service.api;

import by.it_academy.jd2.crm.model.Department;
import by.it_academy.jd2.crm.model.Position;

import java.util.List;

public interface IPositionDepartmentService {
    int getCount(String value);
    List<Department> getAllDepartments();
    String getNameDepartment(long id);
    List<Position> getAllPositions();
    Position getPosition(long id);
    Department getDepartment(long id);
}
