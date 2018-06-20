package com.zhiliao.handler;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;

/**
 * 异常发送邮件处理类
 *
 * @author zhangqh
 * @date 2018年6月15日
 */
public class EmailMonitorExceptionProcess {
	
	private static Logger logger = Logger.getLogger(EmailMonitorExceptionProcess.class);
	
	@Autowired  
    JavaMailSender mailSender; 
	
	/**
	 * 异步发送邮件
	 * @param subject 主题
	 * @param text 内容
	 * @param from 发送者
	 * @param to 给谁发送
	 */
	@Async
	public void asyncSendEmailText(String subject, String text, String from,
			String[] to) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();  
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage);  
        try {
			message.setFrom(from);
			message.setTo(to);  
	        message.setSubject(subject);  
	        message.setText(text);  
	        mailSender.send(mimeMessage);  
		} catch (MessagingException e) {
			e.printStackTrace();
			logger.error(e.getStackTrace());
		}  
	}
}
