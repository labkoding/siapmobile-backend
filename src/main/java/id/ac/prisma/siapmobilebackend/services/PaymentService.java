package id.ac.prisma.siapmobilebackend.services;

import id.ac.prisma.siapmobilebackend.data.model.TbTransactions;
import id.ac.prisma.siapmobilebackend.data.repo.TbTransactionsRepository;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {
    @Autowired
    TbTransactionsRepository tbTransactionsRepository;

    public Map submitTrx(
            String amount,
            String transactionDate,
            String paymentMethod,
            String merchantName
    ){
        Map response = new HashMap();
        //        1.2. Create Transaksi ke Database
        TbTransactions tbTransactions = new TbTransactions();
        try {
            tbTransactions.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(transactionDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tbTransactions.setCheckoutAmount(new BigDecimal(amount));
        tbTransactions.setPaymentMethod(paymentMethod);
        tbTransactions.setMerchantName(merchantName);
        tbTransactions.setCreatedDt(new Date());
        tbTransactionsRepository.save(tbTransactions);

//        1.3. Request QR ke Bank
        String apiBank = "http://127.0.0.1:8080/bank/generate-qr";
        String dataQR = "";
        try {
            URL url = new URL(apiBank);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = "{\"qty\":100,\"name\":\"iPad 4\"}";

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output="";
            String outputCharacter;
            System.out.println("Output from Server .... \n");
            while ((outputCharacter = br.readLine()) != null) {
                output += outputCharacter;
            }
            System.out.println(output);
            // convert string to json
            JSONObject jsonObject = new JSONObject(output);
            dataQR = jsonObject.getString("dataQR");
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        1.4. Save data QR ke Database

//        1.5. Kirim Response Data QR ke Merchant

        response.put("amount", amount);
        response.put("transaction_date", transactionDate);
        response.put("payment_method", paymentMethod);
        response.put("merchant_name", merchantName);
        response.put("dataQR", dataQR);
        return response;
    }

}
