package com.example.vicharanapplication.Responses;

public class LogInResponse {
        public String username;
        public String password;
        public String response;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getResponse() {
        return response;
    }

    public void setToken(String token) {
        this.response = token;
    }
}
