package id.ac.prisma.siapmobilebackend.simulator.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = {"/bank"})
public class BankController {
    @RequestMapping(value = "/generate-qr", method = RequestMethod.POST, produces = "application/json")
    public Map generateQr(@RequestBody Map bodyRequest) {
        Map response = new HashMap();
        String dataQR = "ini adalah data qr yang nantinya akan di convert menjadi gambar qr oleh website atau aplikasi";
        response.put("dataQR", dataQR);
        return response;
    }
}
