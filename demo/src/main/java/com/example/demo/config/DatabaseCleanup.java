package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;

@Component
@Slf4j
public class DatabaseCleanup {

    @Autowired
    private DataSource dataSource;

    @PreDestroy
    public void cleanup() {
        try {
            log.info("Closing datasource...");
            dataSource.getConnection().close();
            log.debug("Datasource closed!!");
        } catch (Exception e) {
            log.error("Error closing datasource: {}", ExceptionUtils.getMessage(e));
        }
    }
}
