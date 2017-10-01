package com.netikras.studies.studentbuddy.api.config;

import com.netikras.studies.studentbuddy.api.aop.advices.AuthorizableActionsAdvice;
import com.netikras.studies.studentbuddy.api.filters.AuthorizationFilter;
import com.netikras.studies.studentbuddy.commons.P;
import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.tools.common.properties.PropertiesAssistant;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by netikras on 17.6.21.
 */
@Configuration
@EnableAspectJAutoProxy()
@ComponentScan(basePackages = {
        P.BASE_PACKAGE + ".api",
        P.BASE_PACKAGE + ".core",
})
@EnableJpaRepositories(basePackages = {
        P.BASE_PACKAGE + ".core.data.api.dao",
        P.BASE_PACKAGE + ".core.data.sys.dao"
})
@PropertySource({"classpath:persistence-jndi.properties"})
//@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
@EnableTransactionManagement(mode = AdviceMode.PROXY)
public class ApiConfig {

    String[] datamodelPackages = {
            P.BASE_PACKAGE + ".core.data.api.model",
            P.BASE_PACKAGE + ".core.data.sys.model"
    };


    @Resource
    private Environment env;
//
//    @Bean
//    public AuthorizableActionsAdvice aspectBeanAuthorization() {
//        return new AuthorizableActionsAdvice();
//    }

    @Bean(name = "configProperties")
    public PropertiesWrapper propertiesWrapper(Environment environment) {
        return new PropertiesWrapper(environment);
    }

    @Bean(destroyMethod = "")
    public DataSource dataSource(PropertiesWrapper propsw) {
        JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        DataSource dataSource = dsLookup.getDataSource(propsw.getValue("db.jndi.name", ""));
        return dataSource;
//        return new EmbeddedDatabaseBuilder().setType(H2).setName("studbuddb").build();
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter,
                                                                       PropertiesWrapper propsw) {
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setDataSource(dataSource);
        lef.setJpaVendorAdapter(jpaVendorAdapter);
        lef.setPackagesToScan(datamodelPackages);


        Properties props;
        props = PropertiesAssistant.getPrefixedProperties(propsw.props, "hibernate", false);

        props.setProperty("hibernate.dialect", propsw.getValue("hibernate.dialect", ""));
        props.setProperty("hibernate.hbm2ddl.auto", propsw.getValue("hibernate.hbm2ddl.auto", "validate"));
        props.setProperty("hibernate.hbm2ddl.import_files", propsw.getValue("hibernate.hbm2ddl.import_files", ""));
        props.setProperty("hibernate.show_sql", propsw.getValue("hibernate.show_sql", "false"));
        props.setProperty("hibernate.format_sql", propsw.getValue("hibernate.format_sql", "false"));

        lef.setJpaProperties(props);

        return lef;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter(PropertiesWrapper propsw) {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(propsw.getValue("db.show.sql", false));
        hibernateJpaVendorAdapter.setGenerateDdl(propsw.getValue("db.generate.ddl", true));
//        hibernateJpaVendorAdapter.setDatabase(Database.H2);
        return hibernateJpaVendorAdapter;
    }

    @SuppressWarnings("SpringJavaAutowiringInspection") // stupid idea does not see a bean two methods above..
    @Bean
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean.getObject());
        return transactionManager;
    }


    @Bean
    public AuthorizationFilter authorizationFilter() {
        return new AuthorizationFilter();
    }


    class PropertiesWrapper {

        private Environment env = null;
        private Properties props = null;


        public PropertiesWrapper(Environment environment) {
            this.env = environment;
        }

        public PropertiesWrapper(Properties properties) {
            this.props = properties;
        }


        public String getValue(String name, String defaultValue) {
            String value = null;

            if (env != null) {
                value = env.getProperty(name, defaultValue);
            } else if (props != null) {
                value = props.getProperty(name, defaultValue);
            } else {
                throw new StudBudUncheckedException("Properties source is not available.");
            }

            return value;
        }

        public boolean getValue(String name, boolean defaultValue) {
            String value = getValue(name, "" + defaultValue);
            boolean booleanValue = Boolean.parseBoolean(value);
            return booleanValue;
        }

        public long getValue(String name, long defaultValue) {
            String value = getValue(name, "" + defaultValue);
            long longValue = defaultValue;

            try {
                longValue = Long.parseLong(value);
            } catch (NumberFormatException e) {

            }

            return longValue;
        }

        public long getValue(String name, int defaultValue) {
            String value = getValue(name, "" + defaultValue);
            int intValue = defaultValue;

            try {
                intValue = Integer.parseInt(value);
            } catch (NumberFormatException e) {

            }

            return intValue;
        }

    }

}
