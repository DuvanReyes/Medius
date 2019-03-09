/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Arco02
 */
public class Mailer {
         public static void sendBienvenido(String para,  String nombUsu, String clave) {
        final String user = "valufor@gmail.com";//cambiará en consecuencia al servidor utilizado
        final String pass = "oscar0380443";

//1st paso) Obtener el objeto de sesión
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "25");
        props.setProperty("mail.smtp.starttls.required", "false");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

//2nd paso)compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
            message.setSubject("Medius Software");

            message.setContent(
                    "<center><img src='http://fs5.directupload.net/images/160530/khs5cmdc.jpg' title='Banco Pedagogico'></center>"
                    + "<h3> Bienvenido. "
                    + "</h3>"
                    + "Datos de Ingreso al sistema: "
                    + "<h4> Su  Usuario : "
                    + nombUsu
                    + "</h4>"
                    + "<h4> Su clave es: "
                    + clave
                    + " </h4>", "text/html");

            //3rd paso)send message
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    
    
    

}
