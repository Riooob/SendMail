package com.sendemail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

    public static void main(String[] args) {

        // почта получателя
        String to = "fromaddress@gmail.com";

        // почта отправителя
        String from = "toaddress@gmail.com";

        // отправляем письмо через gmails smtp
        String host = "smtp.gmail.com";

        // получаем системные свойства
        Properties properties = System.getProperties();

        // настройка почтового серврера
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // получаем объект Session и передаем имя пользователя и пароль
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                    //login gmail -> manage your google account -> security -> Signing in to Google -> App passwords (create new app password)
                return new PasswordAuthentication("toaddress@gmail.com", "AppPassword");

            }

        });


        session.setDebug(true);

        try {

            MimeMessage message = new MimeMessage(session);


            message.setFrom(new InternetAddress(from));


            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));


            message.setSubject("This is the Subject Line!");


            message.setText("This is actual message");

            System.out.println("sending...");

            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

}