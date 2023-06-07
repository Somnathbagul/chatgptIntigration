package com.sb.example.chatgpt.entity;

import com.sb.example.chatgpt.constants.ChatGptConstant;

import java.util.ArrayList;
import java.util.List;
public class ChatGptReqMessage {
    private String model;
    private double temperature;
    private List<Messages> messages;

    public ChatGptReqMessage(String content, String model, String temperature) {
        this.model = model;
        this.messages = new ArrayList<>();
        messages.add(new Messages(ChatGptConstant.USER,content));
        this.temperature = Double.parseDouble(temperature);
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public List<Messages> getMessages() {
        return messages;
    }

    public void setMessages(List<Messages> messages) {
        this.messages = messages;
    }


}
