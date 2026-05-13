package com.servicehub.auth.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOtpEmail(String to, String otp){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Service Password Reset OTP");
        message.setText(
                "Your OTP is: "+ otp + "\nThis OTP is valid for 5 minutes.\n\nIf you didn't request this, ignore."
        );
        mailSender.send(message);
    }
}
