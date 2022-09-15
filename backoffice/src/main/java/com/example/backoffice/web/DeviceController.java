package com.example.backoffice.web;

import com.example.backoffice.entities.Association;
import com.example.backoffice.entities.Device;
import com.example.backoffice.entities.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Arrays;

@Controller
@AllArgsConstructor
public class DeviceController {
    RestTemplate restTemplate;

    @GetMapping(path = "/devices")
    public String devices(Model model){
        String url = "http://localhost:8082/devices";

        Gson gson = new GsonBuilder().create();

        JsonElement element = retrieve(url, "devices", gson);
        Device[] devices = gson.fromJson(element, Device[].class);

        model.addAttribute("devices", Arrays.asList(devices));
        return "devices";
    }

    @GetMapping(path = "associate")
    public String associate(Model model){
        Gson gson = new GsonBuilder().create();

        String url = "http://localhost:8082/devices";
        JsonElement element = retrieve(url, "devices", gson);
        Device[] devices = gson.fromJson(element, Device[].class);


        url = "http://localhost:8081/users";
        element = retrieve(url, "users", gson);
        User[] userList = gson.fromJson(element, User[].class);

        model.addAttribute("users", Arrays.asList(userList));

        model.addAttribute("devices", devices);
        model.addAttribute("association", new Association());
        return "associate";
    }

    @PostMapping(path = "/save")
    public String save(Model model, @ModelAttribute @Valid Association association, BindingResult bindingResult){
        System.out.println(association);
        if(bindingResult.hasErrors())  return "associate";

        String url = "http://localhost:8081/users/" + association.getUser();
        User user = restTemplate.getForObject(url, User.class);

        url = "http://localhost:8082/devices/" + association.getDevice();
        Device device = restTemplate.getForObject(url, Device.class);

        device.setUser(user);

        url = "http://localhost:8082/device/user/"+ association.getUser();

        restTemplate.put(url, device, Device.class);

        return "redirect:/devices";
    }

    public JsonElement retrieve(String url, String item, Gson gson){
        String obj = restTemplate.exchange(url, HttpMethod.GET, null, String.class).getBody();

        JsonObject jsonObject = gson.fromJson(obj, JsonObject.class);

        return jsonObject.getAsJsonObject("_embedded").getAsJsonArray(item);
    }
}
