package by.it_academy.jd2.crm.service.spring;

import by.it_academy.jd2.crm.model.ConfigDB;
import by.it_academy.jd2.crm.storage.ConfigDBStorage;
import by.it_academy.jd2.crm.storage.api.IEmployerStorage;
import by.it_academy.jd2.crm.storage.api.IPositionDepartmentStorage;
import by.it_academy.jd2.crm.storage.spring.EmployerHibStorageSpring;
import by.it_academy.jd2.crm.storage.spring.PosDepHibStorageSpring;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

//@Configuration
public class DaoConfig {



}
