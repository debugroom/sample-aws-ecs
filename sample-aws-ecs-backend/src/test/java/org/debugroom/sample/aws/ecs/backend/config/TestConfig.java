package org.debugroom.sample.aws.ecs.backend.config;

import org.debugroom.sample.aws.ecs.backend.WebApp;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
/*
@ComponentScan("org.debugroom.sample.aws.ecs.backend.domain.repository")
 */
@EnableJpaRepositories(basePackages={
        "org.debugroom.sample.aws.ecs.backend.domain.repository"})
@EntityScan("org.debugroom.sample.aws.ecs.backend.domain.entity")
@Import(WebApp.class)
public class TestConfig {

    /*

    @Autowired
    DataSource dataSource;

    @Bean
    public PlatformTransactionManager transactionManager() throws Exception{
        return new JpaTransactionManager();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){

        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();

        Properties properties = new Properties();
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");

        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setPackagesToScan("org.debugroom.sample.aws.ecs.backend.domain.entity");
        emfb.setJpaProperties(properties);
        emfb.setJpaVendorAdapter(adapter);
        emfb.setDataSource(dataSource);

        return emfb;

    }
     */

}
