package <%=groupId%>.config;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Configuration
@ConfigurationProperties("mailer")
public class MailerConfiguration {

    @Autowired
    JavaMailSender sender;
    private String[] recipients;
    private String from;

    public MimeMessage createMessage() throws MessagingException {
        MimeMessage message = sender.createMimeMessage();

        // use the true flag to indicate you need a multipart message
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(recipients);
        helper.setFrom(from);

        return message;
    }

    public void send(MimeMessage message) {
        sender.send(message);
    }

    public String[] getRecipients() {
        return recipients;
    }

    public void setRecipients(String[] recipients) {
        this.recipients = recipients;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

}
