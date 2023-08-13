package core.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.hibernate.dialect.MySQLDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("web.*.*.impl")
@EnableTransactionManagement
public class TestConfig {

	@Bean
    public DataSource dataSource() {
        return new DriverManagerDataSource("jdbc:mysql:///JAVA_FRAMEWORK", "root", "killerplay13");
    }
    @Bean
    public SessionFactory sessionFactory() {
        return new LocalSessionFactoryBuilder(dataSource())
            .scanPackages("web.*.entity")
            .addProperties(getHibernateProperties())
            .buildSessionFactory();
    }
    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", MySQLDialect.class.getName());
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty(
            "hibernate.current_session_context_class", SpringSessionContext.class.getName()
        );
        return properties;
    }
    @Bean
    public TransactionManager transactionManager() {
        return new HibernateTransactionManager(sessionFactory());
    }

}
