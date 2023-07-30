package com.hxg.mail.springboot.config;

import com.hxg.mail.core.MailAccountManager;
import com.hxg.mail.core.SendMailService;
import com.hxg.mail.core.SendMailServiceImpl;
import com.hxg.mail.core.handler.RsaPasswdHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 邮件自动配置类
 */
@Configuration
@ConditionalOnClass(value = {SendMailService.class, SendMailServiceImpl.class})
public class HxgMailAutoConfigure {

    @Bean
    @ConditionalOnMissingClass
    public MailAccountManager mailAccountManager() {
        MailAccountManager mailAccountManager = new MailAccountManager();
        mailAccountManager.setPasswdHandler(new RsaPasswdHandler());
        return mailAccountManager;
    }

    @Bean
    @ConditionalOnMissingClass
    public SendMailService sendMailService() {
        return new SendMailServiceImpl(mailAccountManager());
    }
}
