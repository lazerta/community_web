package com.shawn.community.sms.util;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
@Data
public class Util {
    @Value("${twilio.trial_number}")
    private String trialNumber;
    @Value("${twilio.account_sid}")
    private String accountSID;
    @Value("${twilio.auth_token}")
    private String authToken;
    private PhoneNumber from;


    @PostConstruct
    public void init() {
        Twilio.init(accountSID, authToken);
        from = new PhoneNumber(trialNumber);
    }

    public void sendMessageTo(String numberToSend, String body) {
        PhoneNumber sendTo = new PhoneNumber(numberToSend);

        Message message = Message.creator(sendTo, from, body).create();

    }

}
