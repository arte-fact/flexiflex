package com.m2i.flexiflex.utils;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

// -----------------------------------------------------------------------------------
// -------------------- Fonction qui envoie l'email. A instancier --------------------
// -----------------------------------------------------------------------------------
//
//    public static void main(String[] args) {
//
//        String userEmail = "florent.chazot@gmail.com";
//
//
//        SendMail send_mail    =   new SendMail();
//        send_mail.sendMail("flexiflex.emailvalidation@gmail.com", userEmail, "Test Mail", "Hi this is Test mail from Java Srilankan Support");
//    }


public class SendMail {

    public static void sendMail(String m_from,String m_to,String m_subject,String m_body){

        try {
            final String username = "flexiflex.emailvalidation@gmail.com"; // Mettre l'email de Flexiflex
            final String password = "Flexiflex2018"; // Mettre le password de Flexiflex

            Properties m_properties = new Properties();
            m_properties.put("mail.smtp.host", "smtp.gmail.com");
            m_properties.put("mail.smtp.socketFactory.port", "465");
            m_properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
            m_properties.put("mail.smtp.auth", "true");
            m_properties.put("mail.smtp.port", "465");

            // username and the password
            Session m_Session = Session.getDefaultInstance(m_properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password); // username and the password
                }
            });

            Message m_simpleMessage = new MimeMessage(m_Session);
            InternetAddress m_fromAddress = new InternetAddress(m_from);
            InternetAddress m_toAddress = new InternetAddress(m_to);

            m_simpleMessage.setFrom(m_fromAddress);
            m_simpleMessage.setRecipient(RecipientType.TO, m_toAddress);
            m_simpleMessage.setSubject(m_subject);
            m_simpleMessage.setContent(m_body,"text/html; charset=\"UTF-8\"");

            Transport.send(m_simpleMessage);

        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
}


//http://localhost:3306/email_validation?key1:uuid&key2=validationtoken