package id.ac.prisma.siapmobilebackend.controlers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = {"/login"})
public class LoginController {

    String accessToken = "";

    /**
     * fungsi untuk menerima data dari aplikasi mobile
     * untuk kebutuhan login. datanya adalah:
     * email dan password
     * setelah terima data tersebur, selanjutnya di validasi apakah email dan password
     * tersebut valid
     * jika valid maka buatkan  accsessToken, jika tidak kasi response gagal
     */

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public String login(String email, String password) {
        //validasi
        if (email == null) {
            // response gagal email salah
            return null;
        }
        if (email == "") {
            // response gagal email salah
            return null;
        }
        if (password == null) {
            // response gagal password salah
            return null;
        }
        if (password == "") {
            // response gagal
            return null;
        }
        //kita buatkan accesstoken
        String accessToken = UUID.randomUUID().toString();
        return accessToken;
    }
}