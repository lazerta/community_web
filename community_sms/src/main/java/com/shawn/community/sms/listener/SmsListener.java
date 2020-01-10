package com.shawn.community.sms.listener;

import com.shawn.community.sms.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "sms")
@AllArgsConstructor
public class SmsListener {
    private final Util util;
    private final String message= "Your check code is ";


    @RabbitHandler
    public void executeSms(Map<String, String> map) {
        String checkCode = map.get("SMS_CODE_");
        String phoneNumber = map.get("mobile");
       try {
           util.sendMessageTo(phoneNumber, message+checkCode +". It will be valid for 5 minutes");
       }catch (Exception e){
           e.printStackTrace();

       }
    }
}
