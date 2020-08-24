package dao;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import service.*;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan({"dao"})
@ImportResource({"spring-servlet.xml"})
@EnableTransactionManagement
public class ProjectConfig {

    @Bean
    @Scope("prototype")
    public CategoryEntityServiceImpl categoryEntityService() {
        return new CategoryEntityServiceImpl();
    }

    @Bean
    @Scope("prototype")
    public CategoryServiceImpl categoryService() {
        return new CategoryServiceImpl();
    }

    @Bean
    @Scope("prototype")
    public DayOffRequestServiceImpl dayOffRequestService() {
        return new DayOffRequestServiceImpl();
    }

    @Bean
    @Scope("prototype")
    public EmailServiceImpl emailService() {
        return new EmailServiceImpl();
    }

    @Bean
    @Scope("prototype")
    public FileServiceImpl fileService() {
        return new FileServiceImpl();
    }

    @Bean
    @Scope("prototype")
    public UserServiceImpl userService() {
        return new UserServiceImpl();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/company-management?useUnicode=yes&characterEncoding=UTF-8");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }

    private Properties HibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
        hibernateProperties.put("hibernate.show_sql", "true");
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        hibernateProperties.put("hibernate.connection.CharSet", "utf8");
        hibernateProperties.put("hibernate.connection.characterEncoding", "utf8");
        hibernateProperties.put("hibernate.connection.useUnicode", "true");
        return hibernateProperties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean =
                new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("entity");
        factoryBean.setJpaProperties(HibernateProperties());
        return factoryBean;
    }

    @Bean
    @Autowired
    public PlatformTransactionManager transactionManager(
            EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
