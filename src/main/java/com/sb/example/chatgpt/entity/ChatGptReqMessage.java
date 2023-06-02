package com.sb.example.chatgpt.entity;

import com.sb.example.chatgpt.constants.ChatGptConstant;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
public class ChatGptReqMessage {
    private String model;
    private List<Messages> messages;
    private double temperature;

    private String max_token;

    @Value("${api.model}")
    private String apiModel;
    @Value("${api.max_token}")
    private String apiMaxToken;
    public ChatGptReqMessage(String content) {
        //this.model = ChatGptConstant.MODEL;
        this.model = apiModel;
        messages = new ArrayList<Messages>();
        messages.add(new Messages(ChatGptConstant.USER,content));
        this.messages = messages;
        //this.temperature = 0.5;
        this.max_token=apiMaxToken;
    }
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Messages> getMessages() {
        return messages;
    }

    public void setMessages(List<Messages> messages) {
        this.messages = messages;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getMax_token() {
        return max_token;
    }

    public void setMax_token(String max_token) {
        this.max_token = max_token;
    }
}
