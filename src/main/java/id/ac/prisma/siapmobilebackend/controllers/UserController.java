package id.ac.prisma.siapmobilebackend.controllers;

import id.ac.prisma.siapmobilebackend.data.model.TbUser;
import id.ac.prisma.siapmobilebackend.data.repo.TbUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = {"/user"})
public class UserController {

    @Autowired
    TbUserRepository tbUserRepository;

    @RequestMapping(value = "/by-id/{id}", method = RequestMethod.GET, produces = "application/json")
    public Map getUserById(@PathVariable("id") Integer id) {
        Map response = new HashMap();
        TbUser tbUser = tbUserRepository.findById(id).orElse(null);
        response.put("data", tbUser);
        return response;
    }

    @RequestMapping(value = "/by-email", method = RequestMethod.POST, produces = "application/json")
    public Map byEmail(@RequestBody Map bodyRequest) {
        // query data user berdasarkan email
        // call tbUserRepository.findByEmail
        String email = bodyRequest.get("email").toString();
        Optional<TbUser> tbUser = tbUserRepository.findByEmail(email);
        TbUser result = tbUser.orElse(null);

        Map response = new HashMap();
        response.put("data", result);
        return response;
    }

    @RequestMapping(value = "/create-user", method = RequestMethod.POST, produces = "application/json")
    public Map createUser(@RequestBody Map bodyRequest) {
        // insert data
        // call tbUserRepository.save
        TbUser tbUser = new TbUser();
        tbUser.setFullName(bodyRequest.get("full_name").toString());
        tbUser.setEmail(bodyRequest.get("email").toString());
        tbUser.setPassword(bodyRequest.get("password").toString());
        tbUser = tbUserRepository.save(tbUser);

        Map response = new HashMap();
        response.put("data", tbUser);
        return response;
    }







    @RequestMapping(value = "/siswa", method = RequestMethod.GET, produces = "application/json")
    public Map siswa() {
        Map response = new HashMap();
        response.put("student_name", "Yura");
        return response;
    }
    @RequestMapping(value = "/payment", method = RequestMethod.POST, produces = "application/json")
    public Map payment(@RequestBody Map data) {
        String myName = (String) data.get("yourname");
        Map response = new HashMap();
        response.put("status", "success");
        response.put("customer", myName);
        return response;
    }
    @RequestMapping(value = "/get-all-users", method = RequestMethod.GET, produces = "application/json")
    public Iterable<TbUser> getAllUsers(){
        Iterable<TbUser> allUser = tbUserRepository.findAll();
        return allUser;
    }
}
