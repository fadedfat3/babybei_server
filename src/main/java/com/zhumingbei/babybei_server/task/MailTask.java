package com.zhumingbei.babybei_server.task;

import com.zhumingbei.babybei_server.service.impl.MailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

/**
 * @author fadedfate
 * @date Created at 2019/8/21 17:19
 */
@Component
@Slf4j
@Lazy(false)
public class MailTask implements InitializingBean {

    @Autowired
    private MailServiceImpl mailService;

    @Scheduled(cron = "0 0 0 12 4 ?")
    public void sendMailEveryMinute() throws MessagingException {
        log.info("send mail on my birthday every year");
        mailService.sendMail("1743723571@qq.com", "Happy Birthday", "Happy Birthday To You, fadedfate!", false, "");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //启动程序时立即执行task
        //sendMailEveryMinute();
    }
}
