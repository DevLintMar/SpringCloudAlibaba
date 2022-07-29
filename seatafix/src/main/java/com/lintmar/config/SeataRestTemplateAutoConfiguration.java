//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.lintmar.config;

import com.alibaba.cloud.seata.rest.SeataRestTemplateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Configuration(
        proxyBeanMethods = false
)
public class SeataRestTemplateAutoConfiguration {
    @Autowired(
            required = false
    )
    private Collection<RestTemplate> restTemplates;
    @Autowired
    private SeataRestTemplateInterceptor seataRestTemplateInterceptor;

    public SeataRestTemplateAutoConfiguration() {
    }

    @PostConstruct
    public void init() {
        if (this.restTemplates != null) {
            Iterator var1 = this.restTemplates.iterator();

            while (var1.hasNext()) {
                RestTemplate restTemplate = (RestTemplate) var1.next();
                List<ClientHttpRequestInterceptor> interceptors = new ArrayList(restTemplate.getInterceptors());
                interceptors.add(this.seataRestTemplateInterceptor);
                restTemplate.setInterceptors(interceptors);
            }
        }

    }

    @Configuration(
            proxyBeanMethods = false
    )
    static class SeataRestTemplateInterceptorConfiguration {
        SeataRestTemplateInterceptorConfiguration() {
        }

        @Bean
        public SeataRestTemplateInterceptor seataRestTemplateInterceptor() {
            return new SeataRestTemplateInterceptor();
        }
    }
}
