package com.sb.example.chatgpt.entity;

public class ChatGptResMessage {
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "ChatGptResMessage{" +
                "response='" + response + '\'' +
                '}';
    }
}
