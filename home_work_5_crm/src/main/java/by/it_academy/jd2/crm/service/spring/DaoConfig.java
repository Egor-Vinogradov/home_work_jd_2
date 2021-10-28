package by.it_academy.jd2.crm.service.spring;

import by.it_academy.jd2.crm.model.ConfigDB;
import by.it_academy.jd2.crm.storage.ConfigDBStorage;
import by.it_academy.jd2.crm.storage.api.IEmployerStorage;
import by.it_academy.jd2.crm.storage.api.IPositionDepartmentStorage;
import by.it_academy.jd2.crm.storage.api.ISearchStorage;
import by.it_academy.jd2.crm.storage.spring.EmployerHibStorageSpring;
import by.it_academy.jd2.crm.storage.spring.PosDepHibStorageSpring;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
public class DaoConfig {

    @Bean
    public IEmployerStorage employerStorage(SessionFactory sessionFactory) {
        return new EmployerHibStorageSpring(sessionFactory);
    }

    @Bean
    public ISearchStorage searchStorage(SessionFactory sessionFactory) {
        return new EmployerHibStorageSpring(sessionFactory);
    }

    @Bean
    public IPositionDepartmentStorage positionDepartmentStorage(SessionFactory sessionFactory) {
        return new PosDepHibStorageSpring(sessionFactory);
    }

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
        builder.scanPackages("by.it_academy.jd2.crm.model");
        builder.setProperty("hibernate.show_sql", "true");
        builder.setProperty("hibernate.hbm2ddl.auto", "update");
        builder.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
        return builder.buildSessionFactory();
    }

    @Bean
    public DataSource dataSource() {
        /**
         * Можно то же через bean, но все сломается
         */
        ConfigDB configDB = ConfigDBStorage.getInstance().getConfigDB();

        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        try {
//            dataSource.setDriverClass(configDB.getDriver());
//            dataSource.setJdbcUrl(configDB.getUrl());
//            dataSource.setUser(configDB.getUser_name());
//            dataSource.setPassword(configDB.getPassword());
            dataSource.setDriverClass("org.postgresql.Driver");
            dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/crm");
            dataSource.setUser("root");
            dataSource.setPassword("root");
            dataSource.setMinPoolSize(5);
            dataSource.setAcquireIncrement(5);
            dataSource.setMaxPoolSize(20);
            dataSource.setMaxStatements(100);
        } catch (PropertyVetoException e) {
            throw new IllegalStateException("Ошибка работы с БД", e);
        }
        return dataSource;
    }

}
