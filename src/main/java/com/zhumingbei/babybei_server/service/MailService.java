package com.zhumingbei.babybei_server.service;

import javax.mail.MessagingException;

/**
 * @author fadedfate
 * @date Created at 2019/8/21 15:43
 */

public interface MailService {
    /**
     * 发送文本邮件
     *
     * @param to       接收者邮件地址
     * @param subject  邮件主题
     * @param content  邮件内容
     * @param html     邮件内容是否为html
     * @param filepath 附件文件路径
     * @param cc       抄送邮件地址
     * @throws MessagingException 邮件发送异常
     */
    void sendMail(String to, String subject, String content, boolean html, String filepath, String... cc) throws MessagingException;
}
