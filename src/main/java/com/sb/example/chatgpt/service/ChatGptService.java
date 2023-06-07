package com.sb.example.chatgpt.service;

import com.sb.example.chatgpt.constants.ChatGptConstant;
import com.sb.example.chatgpt.entity.ChatGptReqMessage;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
@Service
public class ChatGptService {
    @Value("${api.endpoint}")
    private String endpoint;

    @Value("${api.model}")
    private String model;
    @Value("${api.temperature}")
    private String temperature;

    private final RestTemplate restTemplate;

    public ChatGptService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String processMessage(String content, String authorization, MediaType mediaType) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(ChatGptConstant.AUTHORIZATION, authorization);
        headers.setContentType(mediaType);

        ChatGptReqMessage chatGptReqMessage = new ChatGptReqMessage(content,model,temperature);

        HttpEntity<ChatGptReqMessage> requestEntity = new HttpEntity<>(chatGptReqMessage, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(endpoint.trim(), HttpMethod.POST, requestEntity, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
           return String.valueOf(ResponseEntity.ok().build());
        }
    }
}
