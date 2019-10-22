package br.com.biot_admin.biot_admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import static br.com.biot_admin.biot_admin.modules.usuario.enums.EPermissao.APP_OWNER;
import static br.com.biot_admin.biot_admin.modules.usuario.enums.EPermissao.BIOT_ADMIN;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;

    private static final String APPLICATION_CLIENT = "biot_admin-client";
    private static final String APPLICATION_SECRET = "biot_admin-secret";
    private static final Integer TOKEN_VALIDITY_SECONDS = 0;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
            .passwordEncoder(passwordEncoder)
            .tokenKeyAccess("permitAll()")
            .checkTokenAccess("isAuthenticated()")
            .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
            .withClient(APPLICATION_CLIENT)
            .secret(bcryptPasswordEncoder.encode(APPLICATION_SECRET))
            .authorizedGrantTypes("password")
            .authorities(BIOT_ADMIN.name(), APP_OWNER.name())
            .scopes("read", "write", "trust")
            .resourceIds("oauth2-resource")
            .accessTokenValiditySeconds(TOKEN_VALIDITY_SECONDS);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
            .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
            .tokenEnhancer(new CustomTokenEnhancer());
    }
}
