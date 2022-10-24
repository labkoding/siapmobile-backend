package id.ac.prisma.siapmobilebackend.controllers;

import id.ac.prisma.siapmobilebackend.data.model.TbUser;
import id.ac.prisma.siapmobilebackend.data.repo.TbUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = {"/user"})
public class UserController {

    @Autowired
    TbUserRepository tbUserRepository;

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
