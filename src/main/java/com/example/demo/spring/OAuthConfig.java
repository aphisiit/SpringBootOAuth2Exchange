//package com.example.demo.spring;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
//import org.springframework.security.oauth2.client.OAuth2RestOperations;
//import org.springframework.security.oauth2.client.OAuth2RestTemplate;
//import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
//import org.springframework.security.oauth2.client.token.AccessTokenRequest;
//import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
//import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//@EnableOAuth2Client
//@Configuration
//public class OAuthConfig {
//    @Value("${engine.jwt2.url.authen}")
//    protected String JWT2_URL_AUTHEN;
//
//    @Value("${engine.jwt2.client.id}")
//    protected String JWT2_CLIENT_ID;
//
//    @Value("${engine.jwt2.secret}")
//    protected String JWT2_SECRET;
//
//    @Value("${engine.jwt2.nonadmin.user}")
//    protected String JWT2_NONADMIN_USER;
//
//    @Value("${engine.jwt2.nonadmin.password}")
//    protected String JWT2_NONADMIN_PASSWORD;
//
//    @Bean
//    protected OAuth2ProtectedResourceDetails resource() {
//
//        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
//
//        List scopes = new ArrayList<String>(2);
//        scopes.add("write");
//        scopes.add("read");
//        resource.setAccessTokenUri(JWT2_URL_AUTHEN);
//        resource.setClientId(JWT2_CLIENT_ID);
//        resource.setClientSecret(JWT2_SECRET);
//        resource.setGrantType("password");
//        resource.setScope(scopes);
//
//        resource.setUsername(JWT2_NONADMIN_USER);
//        resource.setPassword(JWT2_NONADMIN_PASSWORD);
//
//        return resource;
//    }
//
//    @Bean
//    public OAuth2RestOperations restTemplate() {
//        AccessTokenRequest atr = new DefaultAccessTokenRequest();
//
//        return new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext(atr));
//    }
//}
