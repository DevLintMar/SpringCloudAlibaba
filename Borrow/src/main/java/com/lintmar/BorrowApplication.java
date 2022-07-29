package com.lintmar;

import com.alibaba.cloud.seata.rest.SeataRestTemplateAutoConfiguration;
import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author LintMar
 * @date 2022/7/25
 **/
@EnableFeignClients
@EnableResourceServer
@EnableAutoDataSourceProxy
@SpringBootApplication(exclude = SeataRestTemplateAutoConfiguration.class)
public class BorrowApplication {
    public static void main(String[] args) {
        SpringApplication.run(BorrowApplication.class, args);
    }
}
