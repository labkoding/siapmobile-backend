package id.ac.prisma.siapmobilebackend.controllers;

import id.ac.prisma.siapmobilebackend.data.model.TbUser;
import id.ac.prisma.siapmobilebackend.data.repo.TbUserRepository;
import id.ac.prisma.siapmobilebackend.models.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = {"/login"})
public class LoginController {

    @Autowired
    private TbUserRepository tbUserRepository;

    public String accessToken = "";

    /**
     * fungsi untuk menerima data dari aplikasi mobile
     * untuk kebutuhan login. datanya adalah:
     * email dan password
     * setelah terima data tersebur, selanjutnya di validasi apakah email dan password
     * tersebut valid
     * jika valid maka buatkan  accsessToken, jika tidak kasi response gagal
     */

        @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
        public Map login(@RequestBody LoginRequest loginRequest) {
            String password = loginRequest.getPassword();
            // password sha256 hashing

            String email = loginRequest.getEmail();

            String kulkas = "ikan";
            Map response = new HashMap();
            //validasi
            if (email == null) {
                // response gagal email salah
                response.put("message","email null");
                return response;
        }
        if (email == "") {
            // response gagal email salah
            response.put("message","email kosong");
            return response;
        }
        if (password == null) {
            // response gagal password salah
            response.put("message","password null");
            return response;
        }
        if (password == "") {
            // response gagal
            response.put("message","password kosong");
            return response;
        }

        TbUser userDetail = tbUserRepository.findByEmail(email).orElse(null);
        if (userDetail == null) {
            // response gagal
            response.put("message","email tidak terdaftar");
            return response;
        }

        if (!userDetail.getPassword().equals(password)) {
            // response gagal
            response.put("message","password salah");
            return response;
        }

        //kita buatkan accesstoken
        String accessToken = UUID.randomUUID().toString();
        response.put("accessToken", accessToken);
        response.put("status", "success");
        return response;
    }
}