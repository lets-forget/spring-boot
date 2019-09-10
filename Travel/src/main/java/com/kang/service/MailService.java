package com.kang.service;

import javax.mail.MessagingException;

public interface MailService {
    void sendMain(String from,String subjet,String text) throws MessagingException;
}
