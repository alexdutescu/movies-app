package com.jfsd.movieadmin.bean;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
@Component
public class SchemaInit implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof DataSource dataSource) {
            String schemaName = "moviebooking";
            try (Connection conn = dataSource.getConnection(); Statement statement = conn.createStatement()) {
                statement.execute("create schema if not exists "+ schemaName +";");
            } catch (SQLException ex) {
                throw new RuntimeException("Failed to create schema "+ schemaName, ex);
            }
        }

        return bean;
    }
}
