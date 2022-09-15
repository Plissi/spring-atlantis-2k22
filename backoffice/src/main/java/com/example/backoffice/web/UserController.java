package com.example.backoffice.web;

import com.example.backoffice.entities.User;
import com.example.backoffice.entities.UserList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Controller
@AllArgsConstructor
public class UserController {
    RestTemplate restTemplate;

    @GetMapping(path = "/users")
    public String users(Model model){
        String url = "http://localhost:8081/users";

        String obj = restTemplate.exchange(url, HttpMethod.GET, null, String.class).getBody();

        Gson gson = new GsonBuilder().create();
        JsonObject jsonObject = gson.fromJson(obj, JsonObject.class);

        JsonElement users = jsonObject.getAsJsonObject("_embedded").getAsJsonArray("users");
        User[] userList = gson.fromJson(users, User[].class);

        model.addAttribute("listUsers", Arrays.asList(userList));
        return "users";
    }

    @GetMapping(path = "/")
    public String home(){
        return "redirect:/users";
    }
}
