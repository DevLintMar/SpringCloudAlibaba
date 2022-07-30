package com.lintmar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * @author LintMar
 * @date 2022/7/28
 **/
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private JwtAccessTokenConverter tokenConverter;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.passwordEncoder(encoder)
                .allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("web")
                .secret(encoder.encode("123456"))
                .autoApprove(true)
                .scopes("book", "user", "borrow")
                .redirectUris("http://localhost")
                .authorizedGrantTypes("authorization_code", "refresh_token");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(manager)
                .authorizationCodeServices(authorizationCodeServices)
                .tokenServices(tokenServices())
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }

    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setClientDetailsService(clientDetailsService);
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setTokenStore(tokenStore);
        defaultTokenServices.setTokenEnhancer(tokenConverter);
        defaultTokenServices.setAccessTokenValiditySeconds(3600);
        defaultTokenServices.setRefreshTokenValiditySeconds(3600 * 24 * 7);
        return defaultTokenServices;
    }
}
