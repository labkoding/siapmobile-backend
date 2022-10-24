package id.ac.prisma.siapmobilebackend.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = {"/greetings"})
public class GreetingsController {
    @RequestMapping(value = "/hello", method = RequestMethod.POST, produces = "application/json")
    public Map hello(@RequestBody Map bodyRequest) {
        String name = (String) bodyRequest.get("name");
        Map response = new HashMap();
        response.put("message", "Hello " + name);
        return response;
    }
    @RequestMapping(value = "/selamat-pagi", method = RequestMethod.POST, produces = "application/json")
    public Map selamatPagi(@RequestBody Map bodyRequest) {
        String name = (String) bodyRequest.get("name");
        Map response = new HashMap();
        response.put("message", "Hello selamat pagi juga " + name);
        return response;
    }
}
