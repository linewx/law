package com.linewx.parser.Processor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lugan on 11/18/2016.
 */
public class AmountProcessor implements Processor {
    private Map<String, Long> amountStandard = new HashMap<>();


    public AmountProcessor(){
        init();

    }

    @Override
    public String getName() {
        return "amount";
    }

    @Override
    public String transform(String source) {
        return "";
    }

    public Long caculateAmount(Long cost) {
        if (cost)
    }

    public Boolean isCharge(String reasonNumber, Long cost) {
        if (reasonNumber != null && reasonNumber.length() >= 5) {
            String reasonCategory = reasonNumber.substring(0, 5);
            return cost >= amountStandard.getOrDefault(reasonCategory, 0L);

        } else {
            System.out.println("不明确的案由");
            return false;
        }
    }

    public void init() {
        amountStandard.put("00202", 2000L); //婚姻家庭、继承纠纷
        amountStandard.put("00201", 1000L); //人格权纠纷
        amountStandard.put("00201", Long.MAX_VALUE); //劳动争议、人事纠纷
        amountStandard.put("00302", 2500L); //著作权合同纠纷
    }


}
