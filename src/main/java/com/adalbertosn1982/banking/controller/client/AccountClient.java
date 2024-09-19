package com.adalbertosn1982.banking.controller.client;

import com.adalbertosn1982.banking.controller.client.exception.ClientApiException;
import com.adalbertosn1982.banking.entity.Account;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

public class AccountClient {
    private static final String RESOURCE_PATH = "/accounts";
    private RestTemplate restTemplate;
    private String url;

    public AccountClient(RestTemplate restTemplate, String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    public List<Account> listAccounts() {

        try {
            //URI resourceUri = new URI(url + RESOURCE_PATH);
            URI resourceUri = URI.create(url + RESOURCE_PATH);
            Account[] accounts = restTemplate.getForObject(resourceUri, Account[].class);
            //assert accounts != null;
            return Arrays.asList(accounts);
        } catch (RestClientResponseException e) {
            throw new ClientApiException(e.getMessage(), e);
        }
        //return List.of(accounts);
    }

}
