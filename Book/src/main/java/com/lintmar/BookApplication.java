package com.lintmar;

import com.alibaba.cloud.seata.rest.SeataRestTemplateAutoConfiguration;
import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author LintMar
 * @date 2022/7/25
 **/
@EnableResourceServer
@EnableAutoDataSourceProxy
@SpringBootApplication(exclude = SeataRestTemplateAutoConfiguration.class)
public class BookApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }
}
