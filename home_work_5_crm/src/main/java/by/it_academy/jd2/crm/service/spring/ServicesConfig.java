package by.it_academy.jd2.crm.service.spring;

import by.it_academy.jd2.crm.model.ConfigDB;
import by.it_academy.jd2.crm.service.EmployersService;
import by.it_academy.jd2.crm.service.PositionDepartmentService;
import by.it_academy.jd2.crm.service.api.IEmployersService;
import by.it_academy.jd2.crm.service.api.IPositionDepartmentService;
import by.it_academy.jd2.crm.storage.ConfigDBStorage;
import by.it_academy.jd2.crm.storage.api.IEmployerStorage;
import by.it_academy.jd2.crm.storage.api.IPositionDepartmentStorage;
import by.it_academy.jd2.crm.storage.api.ISearchStorage;
import by.it_academy.jd2.crm.storage.spring.EmployerHibStorageSpring;
import by.it_academy.jd2.crm.storage.spring.PosDepHibStorageSpring;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
public class ServicesConfig {

    @Bean
    public IEmployersService employersService(IEmployerStorage employerStorage, ISearchStorage searchStorage) {
        return new EmployersService(employerStorage, searchStorage);
    }

    @Bean
    public IPositionDepartmentService positionDepartmentService(IPositionDepartmentStorage positionDepartmentStorage) {
        return new PositionDepartmentService(positionDepartmentStorage);
    }

//    @Bean
//    public IEmployerStorage employerStorage(SessionFactory sessionFactory) {
//        return new EmployerHibStorageSpring(sessionFactory);
//    }
//
//    @Bean
//    public ISearchStorage searchStorage(SessionFactory sessionFactory) {
//        return new EmployerHibStorageSpring(sessionFactory);
//    }
//
//    @Bean
//    public IPositionDepartmentStorage positionDepartmentStorage(SessionFactory sessionFactory) {
//        return new PosDepHibStorageSpring(sessionFactory);
//    }
//
//    @Bean
//    public SessionFactory sessionFactory(DataSource dataSource) {
//        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
//        builder.scanPackages("by.it_academy.jd2.crm.model");
//        builder.setProperty("hibernate.show_sql", "true");
//        builder.setProperty("hibernate.hbm2ddl.auto", "update");
//        builder.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
//        return builder.buildSessionFactory();
//    }
//
//    @Bean
//    public DataSource dataSource() {
//
//        ComboPooledDataSource dataSource = new ComboPooledDataSource();
//
//        try {
//            dataSource.setDriverClass("org.postgresql.Driver");
//            dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/crm");
//            dataSource.setUser("root");
//            dataSource.setPassword("root");
//            dataSource.setMinPoolSize(5);
//            dataSource.setAcquireIncrement(5);
//            dataSource.setMaxPoolSize(20);
//            dataSource.setMaxStatements(100);
//        } catch (PropertyVetoException e) {
//            throw new IllegalStateException("Ошибка работы с БД", e);
//        }
//        return dataSource;
//    }
}
