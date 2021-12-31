package com.example.webX.backend.TechnicalAssignment.common.Utils;

import java.util.Random;
import java.util.UUID;

public class RandomIdUtil {

    public String getUuid() {
        return UUID.randomUUID().toString().replace("-","").substring(0,4);
    }

    public int getRandomInvoiceNo()
    {
        Random rnd = new Random();
       return rnd.nextInt(999999);
    }
}
