package com.example.SpringBootOAuth2Exchange.service;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Service("TokenService")
public class TokenServiceImpl implements TokenService{

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OAuth2RestOperations oAuth2RestOperations;

    @Value("${engine.url}")
    protected String JWT1_ACCESS_TOKEN;

    @Value("${engine.url.user}")
    protected String JWT1_URL_USER;

    @Value("${engine.jwt2.url.authen}")
    protected String JWT2_URL_AUTHEN;

    @Value("${engine.jwt2.nonadmin.user}")
    protected String JWT2_NONADMIN_USER;

    @Value("${engine.jwt2.nonadmin.password}")
    protected String JWT2_NONADMIN_PASSWORD;

    @Value("${engine.jwt2.admin.user}")
    protected String JWT2_ADMIN_USER;

    @Value("${engine.jwt2.admin.password}")
    protected String JWT2_ADMIN_PASSWORD;

    @Value("${engine.jwt2.get.users}")
    protected String JWT2_URL_JWT_USERS;

    @Value("${engine.jwt2.get.yourdata}")
    protected String JWT2_URL_YOUR_DATA;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public ResponseEntity<String> getTokenJWT() {

        LOGGER.info("getTokenJWT()");

        Map<String,String> map = new HashMap<>();
        map.put("username","admin");
        map.put("password","admin");

        Gson gson = new Gson();

        try {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(JWT1_ACCESS_TOKEN,map,String.class);
            Map<String,String> dataMap = gson.fromJson(responseEntity.getBody(),Map.class);
            LOGGER.info(dataMap.get("token"));

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization","Bearer " + dataMap.get("token"));
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
            ResponseEntity<String> responseEntity2 = restTemplate.exchange(JWT1_URL_USER,HttpMethod.GET,entity,String.class);
            LOGGER.info(responseEntity2.getBody());
            return responseEntity2;
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public ResponseEntity<String> getTokenJWT2() {

        LOGGER.info("getTokenJWT2()");

        LOGGER.info("JWT2_NONADMIN_USER : {}",JWT2_ADMIN_USER);
        LOGGER.info("JWT2_NONADMIN_PASSWORD : {}",JWT2_ADMIN_PASSWORD);

        try {
            OAuth2AccessToken oAuth2AccessToken = oAuth2RestOperations.getAccessToken();
            LOGGER.info(oAuth2AccessToken.getTokenType());
            LOGGER.info(oAuth2AccessToken.getValue());

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization","Bearer " + oAuth2AccessToken.getValue());
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

            ResponseEntity<String> responseEntity =  restTemplate.exchange(JWT2_URL_YOUR_DATA,HttpMethod.GET,entity,String.class);
            LOGGER.info("\n" + responseEntity.getBody());

            return responseEntity;
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return null;
        }

    }
}
