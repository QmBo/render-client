package ru.qmbo.renderclient.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.qmbo.renderclient.model.*;

import static java.lang.String.format;

@Service
public class RestTemplateService {
    private final RestTemplate template = new RestTemplate();
    private String token = "0";
    private static final String SERVER = "http://localhost:8080";

    public Task[] getAll() {
        final String baseUrl = format("%s/tasks/list?token=%s", SERVER, this.token);
        ResponseEntity<Task[]> result = this.template.getForEntity(baseUrl, Task[].class);
        return result.getBody();
    }

    public String createUser(String login, String pass) {
        LoginRequest request = new LoginRequest().setLogin(login) .setPassword(pass);
        final String baseUrl = format("%s/users/reg", SERVER);
        ResponseEntity<RegResponse> result = this.template.postForEntity(baseUrl, request, RegResponse.class);
        return result.getBody() != null ? result.getBody().getReg() : "";
    }

    public String loginUser(String login, String pass) {
        LoginRequest request = new LoginRequest().setLogin(login) .setPassword(pass);
        final String baseUrl = format("%s/users/login", SERVER);
        ResponseEntity<LoginResponse> result = this.template.postForEntity(baseUrl, request, LoginResponse.class);
        LoginResponse resultBody = result.getBody();
        if (resultBody != null) {
            this.token = resultBody.getToken();
        }
        return resultBody != null ? resultBody.getStatus() : "";
    }

    public Task createTask() {
        CreateTaskRequest request = new CreateTaskRequest().setToken(this.token);
        final String baseUrl = format("%s/tasks/create", SERVER);
        ResponseEntity<Task> result = this.template.postForEntity(baseUrl, request, Task.class);
        return result.getBody();
    }

    public Stats[] getStats() {
         final String baseUrl = format("%s/tasks/stats?token=%s", SERVER, this.token);
         ResponseEntity<Stats[]> result = this.template.getForEntity(baseUrl, Stats[].class);
         return result.getBody();
    }

    public String getToken() {
        return this.token;
    }
}

