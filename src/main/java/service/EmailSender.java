package service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Properties;

public class EmailSender {
    public static void emailSender() {


        System.out.println("method send email - start");
        final String fromEmail = "redteam.intensive@gmail.com";
        final String toEmail = "zankohannaandreevna@gmail.com"; // нужно будет указать email Юры
        final String password = "Andersen12";

        Properties properties = new Properties();

        properties.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        properties.put("mail.smtp.port", "587"); //TLS Port
        properties.put("mail.smtp.auth", "true"); //enable authentication
        properties.put("mail.smtp.starttls.enable", "true");

        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };

        Session session = Session.getInstance(properties, auth);

        MimeMessage msg = new MimeMessage(session);

        try {

            msg.setFrom(new InternetAddress(fromEmail));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject("Team RED report");

            Multipart email = new MimeMultipart();

            MimeBodyPart pdfFile = new MimeBodyPart();

            pdfFile.attachFile(PDFConverter.PATH_TO_FILE);

            email.addBodyPart(pdfFile);

            msg.setContent(email);
            Transport.send(msg);
            System.out.println("send");

        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }
}
