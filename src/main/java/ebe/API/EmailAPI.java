package ebe.API;

import ebe.DBClasses.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Service
public class EmailAPI {

    private JavaMailSender javaMailSender;

    String cwEmail = "carrers.wales@gmail.com";

    public EmailAPI(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

//    public void sendMail(String name, String fromEmail, String localAuth, String subject, String message) {
//
//        var mailMessage = new SimpleMailMessage();
//
//        mailMessage.setTo(cwEmail);
//        mailMessage.setSubject(subject);
//        mailMessage.setText("You have a new message from " + name + ". Local Authority: " + localAuth + ". \nContent of the message: \n" + message);
//        mailMessage.setFrom(fromEmail);
//
//        javaMailSender.send(mailMessage);
//
//
//    }

//    public void sendRequestByEvent(String emailFrom, String schoolName, String localAuth, String eventName, String eventDate, String eventTime, String eventNotes, String eventType, String guests){
//        var mailMessage = new SimpleMailMessage();
//
//        mailMessage.setTo("carrers.wales@gmail.com");
//        mailMessage.setSubject("Request for " + eventName);
//        mailMessage.setText("You have a new request for " + eventName +
//                "\nSchool Name: " + schoolName +
//                "\nSchool Local Authority: " + localAuth +
//                "\nDate: " + eventDate +
//                "\nTime: " + eventTime +
//                "\nEvent type: " + eventType +
//                "\nAdditional notes: " + eventNotes +
//                "\nProposed guests: " + guests);
//        mailMessage.setFrom(emailFrom);
//        System.out.println(mailMessage);
//
//        javaMailSender.send(mailMessage);
//        System.out.println("sent");
//    }

//    public void sendRequestByIndustry(String emailFrom, String schoolName, String localAuth, String eventName, String eventDate, String eventTime, String eventNotes, String eventType, String industry){
//        var mailMessage = new SimpleMailMessage();
//
//        mailMessage.setTo("carrers.wales@gmail.com");
//        mailMessage.setSubject("Request for " + eventName);
//        mailMessage.setText("You have a new request for " + eventName +
//                "\nSchool Name: " + schoolName +
//                "\nSchool Local Authority: " + localAuth +
//                "\nDate: " + eventDate +
//                "\nTime: " + eventTime +
//                "\nEvent type: " + eventType +
//                "\nAdditional notes: " + eventNotes +
//                "\nProposed Industry type(s): " + industry);
//        mailMessage.setFrom(emailFrom);
//
//        javaMailSender.send(mailMessage);
//    }

//    public void sendRequestByLanguage(String emailFrom, String schoolName, String schoolPostcode, String eventName, String eventDate, String eventTime, String eventNotes, String eventType, String language){
//        var mailMessage = new SimpleMailMessage();
//
//        mailMessage.setTo("carrers.wales@gmail.com");
//        mailMessage.setSubject("Request for " + eventName);
//        mailMessage.setText("You have a new request for " + eventName +
//                "\nSchool Name: " + schoolName +
//                "\nSchool Local Authority: " + schoolPostcode +
//                "\nDate: " + eventDate +
//                "\nTime: " + eventTime +
//                "\nEvent type: " + eventType +
//                "\nAdditional notes: " + eventNotes +
//                "\nProposed language(s): " + language);
//        mailMessage.setFrom(emailFrom);
//
//        javaMailSender.send(mailMessage);
//    }

//    public void sendShowInterest(String emailFrom, String schoolName, String localAuth, String eventName, String eventDate, String eventTime, String eventNotes){
//        var mailMessage = new SimpleMailMessage();
//
//        mailMessage.setTo("carrers.wales@gmail.com");
//        mailMessage.setSubject("Show Interest for " + eventName);
//        mailMessage.setText("You have a new request for " + eventName +
//                "\nSchool Name: " + schoolName +
//                "\nSchool Local Authority: " + localAuth +
//                "\nDate: " + eventDate +
//                "\nTime: " + eventTime +
//                "\nAdditional notes: " + eventNotes);
//        mailMessage.setFrom(emailFrom);
//
//        javaMailSender.send(mailMessage);
//    }

//    public void sendRequestEmployer(String fromEmail, String schoolName, String localAuth, String eventName,
//                                    String eventDate, String eventTime, String eventNotes, String eventType, String employerName){
//
//        var mailMessage = new SimpleMailMessage();
//
//
//        mailMessage.setTo("carrers.wales@gmail.com");
//        mailMessage.setSubject("Request event with employer: " + employerName);
//        mailMessage.setText("There is a new request for event " + eventName +
//                " with employer " + employerName + "." +
//                "\n\nSchool Name: " + schoolName +
//                "\nSchool Local Authority: " + localAuth +
//                "\nDate: " + eventDate +
//                "\nTime: " + eventTime +
//                "\nType: " + eventType +
//                "\nAdditional notes: " + eventNotes);
//        mailMessage.setFrom(fromEmail);
//
//        javaMailSender.send(mailMessage);
//    }







//    TODO: HTML email template.
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    public void getInTouchSubmit(String schoolName, String fromEmail, String localAuth, String subject, String messageBody) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Context context = new Context();
        context.setVariable("schoolName", schoolName);
        context.setVariable("localAuth", localAuth);
        context.setVariable("messageBody", messageBody);

        String html = templateEngine.process("email/getInTouchEmailTemplate", context);

        helper.setTo(cwEmail);
        helper.setText(html, true);
        helper.setSubject(subject);
        helper.setFrom(fromEmail);

        emailSender.send(message);
    }

    public void sendRequestByEvent(String emailFrom, String schoolName, String localAuth, String eventName, String eventDate, String eventTime, String eventNotes, String eventType, String guests) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        //Change comma-separates string into list so that you can put it as a list in html.
        List<String> guestsList = Arrays.asList(guests.split("\\s*,\\s*"));

        Context context = new Context();
        context.setVariable("schoolName", schoolName);
        context.setVariable("localAuth", localAuth);
        context.setVariable("eventName", eventName);
        context.setVariable("eventDate", eventDate);
        context.setVariable("eventTime", eventTime);
        context.setVariable("eventNotes", eventNotes);
        context.setVariable("eventType", eventType);
        context.setVariable("guests", guestsList);

        String html = templateEngine.process("email/requestByEventEmailTemplate", context);

        helper.setTo(cwEmail);
        helper.setText(html, true);
        helper.setSubject("Request for " + eventName);
        helper.setFrom(emailFrom);

        emailSender.send(message);
    }

    public void sendRequestByIndustry(String emailFrom, String schoolName, String localAuth, String eventName, String eventDate, String eventTime, String eventNotes, String eventType, String industry) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        //Change comma-separates string into list so that you can put it as a list in html.
        List<String> industryList = Arrays.asList(industry.split("\\s*,\\s*"));

        Context context = new Context();
        context.setVariable("schoolName", schoolName);
        context.setVariable("localAuth", localAuth);
        context.setVariable("eventName", eventName);
        context.setVariable("eventDate", eventDate);
        context.setVariable("eventTime", eventTime);
        context.setVariable("eventNotes", eventNotes);
        context.setVariable("eventType", eventType);
        context.setVariable("industry", industryList);

        String html = templateEngine.process("email/requestByIndustryEmailTemplate", context);

        helper.setTo(cwEmail);
        helper.setText(html, true);
        helper.setSubject("Request for " + eventName);
        helper.setFrom(emailFrom);

        emailSender.send(message);
    }

    public void sendRequestByLanguage(String emailFrom, String schoolName, String localAuth, String eventName, String eventDate, String eventTime, String eventNotes, String eventType, String language) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        //Change comma-separates string into list so that you can put it as a list in html.
        List<String> languageList = Arrays.asList(language.split("\\s*,\\s*"));

        Context context = new Context();
        context.setVariable("schoolName", schoolName);
        context.setVariable("localAuth", localAuth);
        context.setVariable("eventName", eventName);
        context.setVariable("eventDate", eventDate);
        context.setVariable("eventTime", eventTime);
        context.setVariable("eventNotes", eventNotes);
        context.setVariable("eventType", eventType);
        context.setVariable("language", languageList);

        String html = templateEngine.process("email/requestByLanguageEmailTemplate", context);

        helper.setTo(cwEmail);
        helper.setText(html, true);
        helper.setSubject("Request for " + eventName);
        helper.setFrom(emailFrom);

        emailSender.send(message);
    }

    public void sendShowInterest(String emailFrom, String schoolName, String localAuth, String eventName, String eventDate, String eventTime, String eventNotes) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Context context = new Context();
        context.setVariable("schoolName", schoolName);
        context.setVariable("localAuth", localAuth);
        context.setVariable("eventName", eventName);
        context.setVariable("eventDate", eventDate);
        context.setVariable("eventTime", eventTime);
        context.setVariable("eventNotes", eventNotes);

        String html = templateEngine.process("email/showInterestEmailTemplate", context);

        helper.setTo(cwEmail);
        helper.setText(html, true);
        helper.setSubject(schoolName + " showed interest for " + eventName);
        helper.setFrom(emailFrom);

        emailSender.send(message);
    }

    public void sendRequestEmployer(String fromEmail, String schoolName, String localAuth, String eventName,
                                    String eventDate, String eventTime, String eventNotes, String eventType, String employerName) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Context context = new Context();
        context.setVariable("schoolName", schoolName);
        context.setVariable("localAuth", localAuth);
        context.setVariable("eventName", eventName);
        context.setVariable("eventDate", eventDate);
        context.setVariable("eventTime", eventTime);
        context.setVariable("eventNotes", eventNotes);
        context.setVariable("eventType", eventType);
        context.setVariable("employerName", employerName);

        String html = templateEngine.process("email/requestEmployerEmailTemplate", context);

        helper.setTo(cwEmail);
        helper.setText(html, true);
        helper.setSubject("Request for " + eventName + " with " + employerName);
        helper.setFrom(fromEmail);

        emailSender.send(message);
    }
}
