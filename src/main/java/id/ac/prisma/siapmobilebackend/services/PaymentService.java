package id.ac.prisma.siapmobilebackend.services;

import id.ac.prisma.siapmobilebackend.data.model.TbTransactions;
import id.ac.prisma.siapmobilebackend.data.repo.TbTransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
//        1.4. Save data QR ke Database

//        1.5. Kirim Response Data QR ke Merchant
        String dataQR = "";
        response.put("amount", amount);
        response.put("transaction_date", transactionDate);
        response.put("payment_method", paymentMethod);
        response.put("merchant_name", merchantName);
        response.put("dataQR", dataQR);
        return response;
    }

}
