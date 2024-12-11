/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatapp;
import javax.activation.DataHandler;
import javax.activation.DataSource;
/**
 *
 * @author DELL
 */

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
    private static final String EMAIL = "iamztk1106@gmail.com"; // Email cố định
    private static final String PASSWORD = "vblh fseq losg nemt"; // Mật khẩu ứng dụng

    public static void sendEmail(String toEmail, String subject, String messageText) {
        // Thiết lập thông tin SMTP server
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // Server SMTP
        props.put("mail.smtp.port", "587"); // Cổng SMTP (TLS)
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Tạo session
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL, PASSWORD);
            }
        });

        try {
            // Tạo email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(messageText);

            // Gửi email
            Transport.send(message);
            System.out.println("Email đã được gửi đến: " + toEmail);
        } catch (MessagingException e) {
            System.err.println("Lỗi khi gửi email: " + e.getMessage());
        }
    }
}

