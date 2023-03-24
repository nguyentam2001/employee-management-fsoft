package fa.training.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import fa.training.security.LoginInterceptor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("fa.training")
@PropertySource("classpath:hibernate.properties")
@PropertySource("classpath:jdbc.properties")
public class AppConfig implements WebMvcConfigurer {
    @Autowired
    private Environment environment;

    private Logger logger=Logger.getLogger(getClass().getName());

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");

    }

    // define a bean for ViewResolver
    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    @Bean
    public DataSource dataSource(){
        // create connection pool
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();

        // set the jdbc driver
        try {
            comboPooledDataSource.setDriverClass(environment.getProperty("jdbc.driver"));
        }
        catch (PropertyVetoException exc) {
            throw new RuntimeException(exc);
        }

        // for sanity's sake, let's log url and user ... just to make sure we are reading the data
        logger.info("jdbc.url=" + environment.getProperty("jdbc.url"));
        logger.info("jdbc.user=" + environment.getProperty("jdbc.user"));

        // set database connection props
        comboPooledDataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
        comboPooledDataSource.setUser(environment.getProperty("jdbc.user"));
        comboPooledDataSource.setPassword(environment.getProperty("jdbc.password"));


        // set connection pool props
        comboPooledDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
        comboPooledDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
        comboPooledDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
        comboPooledDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

        return comboPooledDataSource;
    }
    //    convert value property to int

    private int getIntProperty(String propName) {

        String propVal = environment.getProperty(propName);

        // now convert to int
        int intPropVal = Integer.parseInt(propVal);

        return intPropVal;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        // create session factorys
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        // set the properties
        sessionFactory.setDataSource(dataSource());//ref to datasource
        sessionFactory.setPackagesToScan(environment.getProperty("hibernate.packagesToScan"));
        sessionFactory.setHibernateProperties(getHibernateProperties());
        return sessionFactory;
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new CookieLocaleResolver();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);
        registry.addInterceptor(new LoginInterceptor())
                .excludePathPatterns("/login/showLoginPage")
                .excludePathPatterns("/login/authenticateUser")
                .excludePathPatterns("/resources/**");//don't excludePath
    }

    private Properties getHibernateProperties() {
        // set hibernate properties

        Properties props = new Properties();
        props.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        props.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        props.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        props.setProperty("hibernate.cache.provider_class", environment.getProperty("hibernate.cache.provider_class"));
        props.setProperty("hibernate.current_session_context_class", environment.getProperty("hibernate.current_session_context_class"));
        return props;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        // setup transaction manager based on session factory
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }

}
