package com.zhumingbei.babybei_server.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.zhumingbei.babybei_server.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author fadedfate
 * @date Created at 2019/8/21 15:46
 */
@Service
public class MailServiceImpl implements MailService {
    @Autowired
    JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    String from;

    @Override
    public void sendMail(String to, String subject, String content, boolean html, String filePath, String... cc) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, html);
        if (ArrayUtil.isNotEmpty(cc)) {
            helper.setCc(cc);
        }
        if (StrUtil.isNotEmpty(filePath)) {
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);
        }
        mailSender.send(message);
    }

    @Override
    public void sendSimpleMail(String to, String subject, String content, String... cc) throws MessagingException {
        sendMail(to, subject, content, false, "", cc);
    }

}
