package by.it_academy.jd2.crm.storage.api;

import by.it_academy.jd2.crm.model.Department;
import by.it_academy.jd2.crm.model.Position;
import by.it_academy.jd2.crm.model.hibernate.DepartmentsHibernate;
import by.it_academy.jd2.crm.model.hibernate.PositionHibernate;

import java.util.List;

public interface IPositionDepartmentStorage {
    int getCount(String value);
    List<Department> getAllDepartments();
    String getNameDepartment(long id);
    List<Position> getAllPositions();
    Position getPosition(long id);
    Department getDepartment(long id);
    DepartmentsHibernate getDepartmentHibernate(long id);
    PositionHibernate getPositionHibernate(long id);
}
