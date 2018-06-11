package com.hcl.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	      clients.inMemory()
	        .withClient("web")
	        .secret("secret")
	        .authorizedGrantTypes("authorization_code", "refresh_token", "implicit", "password", "client_credentials")
	        .scopes("read","transfer")
	        .and()
	        .withClient("android")
	        .secret("secret")
	        .authorizedGrantTypes("authorization_code", "refresh_token", "implicit", "password", "client_credentials")
	        .scopes("read","transfer");
	}	
	
	

}
