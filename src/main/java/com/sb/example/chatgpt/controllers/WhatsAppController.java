package com.sb.example.chatgpt.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sb.example.chatgpt.constants.ChatGptConstant;
import com.sb.example.chatgpt.entity.ChatGptReqMessage;
import com.sb.example.chatgpt.service.ChatGptService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ChatGptConstant.CONTEXT)
public class WhatsAppController {

    private final ChatGptService chatGptService;
    public WhatsAppController(ChatGptService chatGptService) {
        this.chatGptService = chatGptService;
    }

    @PostMapping(value = ChatGptConstant.CHAT, produces = MediaType.ALL_VALUE)
    @ResponseBody
    public String handleIncomingMessage(@RequestBody String content, @RequestHeader(ChatGptConstant.AUTHORIZATION) String authorization, @RequestHeader(ChatGptConstant.CONTENT_TYPE) MediaType mediaType) {
          // Send the message content to the ChatGPT service for processing
        ChatGptReqMessage chatGptReqMessage = new ChatGptReqMessage(content);
       String response = chatGptService.processMessage(chatGptReqMessage,authorization,mediaType);
       String responseBody = extractContentFromResponse(response);
       return responseBody;
    }

    private String extractContentFromResponse(String response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);

            // Extract the "content" field from the response JSON
            String content = jsonNode.path("choices").path(0).path("message").path("content").asText();

            return content;
        } catch (JsonProcessingException e) {
               e.printStackTrace();
            return "Error occurred";
        }
    }
}
