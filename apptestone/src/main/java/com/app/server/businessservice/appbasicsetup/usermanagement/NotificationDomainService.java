package com.app.server.businessservice.appbasicsetup.usermanagement;
import com.app.bean.EmailBean;

import com.app.bean.NotificationTemplate;

import com.app.shared.appbasicsetup.usermanagement.ArtAppNotificationTemplate;

import com.app.server.repository.appbasicsetup.usermanagement.ArtAppNotificationTemplateRepository;

import com.app.bean.SMSBean;

import com.app.bean.SMSData;

import com.app.server.repository.appbasicsetup.usermanagement.SMSConfigRepository;

import com.app.shared.appbasicsetup.usermanagement.SMSConfig;

import com.app.server.businessservice.appbasicsetup.usermanagement.SMSNotificationBusinessService;

import com.app.bean.NotificationResponseBean;

import java.io.StringWriter;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.context.ApplicationContext;

import com.athena.config.appsetUp.interfaces.AppConfigurationInterface;

/**
 * CLASS IS USED TO SEND EMAIL AND THIS DOMAIN SERVICE IS EXPOSED IN
 * DOMAIN-SERVICE CREATION MODULE IN RAD, USER CAN CREATE THE BEAN HAVING ALL
 * INPUTS EG. EMAIL-SUBJECT, EMAIL-BODY AND RECIPIENTS AND PASS TO SEND-EMAIL
 * METHOD TO AUTOMATE THE PROCESS OF EMAIL SENDING
 */
@Component
public class NotificationDomainService {

	@Autowired
	private AppConfigurationInterface appConfig;

	@Autowired
	private ArtAppNotificationTemplateRepository artAppEmailTemplateRepository;

	@Autowired
	private SMSConfigRepository<SMSConfig> smsConfigRepo;

	@Autowired
	private ApplicationContext applicationContext;

	/** METHOD USED TO INITIATE THE SESSION TO SEND EMAILS */
	private Session getSession(Properties properties) {
		javax.mail.Authenticator authenticator = new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(appConfig.getMailConfig().getUsername(), appConfig.getMailConfig().getPassword());
			}
		};
		return Session.getInstance(properties, authenticator);
	}

	/** METHOD USED TO PREPARE EMAIL CONFIGURATION PRPERTIES */
	private Properties getProperties(String emailSubject, String emailBody) {
		Properties properties = new Properties();
		properties.put("mail.smtp.user", appConfig.getMailConfig().getUsername());
		properties.put("mail.smtp.host", appConfig.getMailConfig().getHost());
		properties.put("mail.smtp.port", appConfig.getMailConfig().getSmtpPort());
		properties.put("mail.smtp.auth", appConfig.getMailConfig().isSmtpAuth());
		properties.put("mail.smtp.starttls.enable", appConfig.getMailConfig().isSmtpTls());
		if (appConfig.getMailConfig().isSmtpSsl()) {
			properties.put("mail.smtp.ssl.enable", appConfig.getMailConfig().isSmtpSsl());
			properties.setProperty("mail.smtp.port", String.valueOf(appConfig.getMailConfig().getSmtpsPort()));
		}
		properties.put("EMAIL_SUBJECT", emailSubject);
		properties.put("EMAIL_BODY", emailBody);
		return properties;
	}

	/**
	 * METHOD USED TO SEND EMAIL - INPUTS EMAIL-SUBJECT, EMAIL-BODY AND
	 * RECIPIENT, WILL ACCEPT ONLY ONE RECIPIENT AT A TIME
	 */
	public NotificationResponseBean sendMail(EmailBean emailBean) throws Exception {
		if (emailBean.sizeOfRecipients() == 0) {
			throw new com.spartan.pluggable.exception.security.InvalidDataException();
		}
		Properties properties = getProperties(emailBean.getEmailSubject(), emailBean.getEmailBody());
		boolean emailStatus = false;
		if ((emailBean.sizeOfAttachmentPath() > 0) || (emailBean.sizeOfAttachmentRelativePathToBaseFilePath() > 0) || (emailBean.sizeOfAttachmentRelativePathToWebAppPath() > 0)) {
			emailStatus = sendEmailWithAttachment(properties, emailBean);
		} else {
			emailStatus = sendEmail(properties, emailBean.getRecipients());
		}
		return new NotificationResponseBean(emailStatus);
	}

	/**
	 * METHOD USED TO SEND EMAIL WITH VELOCITY TEMPLATE
	 * 
	 * @param EmailBean - CONTAINS THE RECIEVERS, ATTACHMENTS AND SUBJECT
	 * @param EmailTemplate - CONTAINS TEMPLATE ID AND DYNAMIC REFERENCES
	 * 
	 * */
	public NotificationResponseBean sendMail(EmailBean emailBean, NotificationTemplate emailTemplate) throws Exception {
		if (emailBean.sizeOfRecipients() == 0) {
			throw new com.spartan.pluggable.exception.security.InvalidDataException();
		}

		/**
		 * PROCESSING THE EMAIL BODY AND REPLACE THE TEMPLATE KEYS BY ITS ACTUAL
		 * VALUES USING EMAIL-TEMPLATE BEAN
		 */
		ArtAppNotificationTemplate artAppEmailTemplate = artAppEmailTemplateRepository.findById(emailTemplate.getTemplateId());
		String emailBody = prepareNotificationBodyByTemplate(emailTemplate, artAppEmailTemplate.getTemplate());

		Properties properties = getProperties(emailBean.getEmailSubject(), emailBody);
		boolean emailStatus = false;
		if (emailBean.getAttachmentPath() != null && emailBean.getAttachmentPath().size() > 0) {
			emailStatus = sendEmailWithAttachment(properties, emailBean);
		} else {
			emailStatus = sendEmail(properties, emailBean.getRecipients());
		}
		return new NotificationResponseBean(emailStatus);
	}

	/**
	 * SEND-EMAIL OVERLOADED METHOD USED TO PREPARE AND TRANSPORT MESSAGE TO
	 * MULTIPLE RECIPIENT
	 */
	private boolean sendEmail(Properties properties, List<String> receipents) {
		try {
			Message message = new MimeMessage(getSession(properties));
			message.setFrom(new InternetAddress(appConfig.getMailConfig().getUsername()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.prepareRecipient(receipents)));
			message.setSubject(properties.getProperty("EMAIL_SUBJECT"));
			message.setContent(properties.getProperty("EMAIL_BODY"), "text/html");
			Transport.send(message);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * SEND-EMAIL METHOD USED TO PREPARE AND TRANSPORT MESSAGE TO MULTIPLE
	 * RECIPIENT WITH ATTACHMENTS
	 */
	private boolean sendEmailWithAttachment(Properties properties, EmailBean emailBean) {
		try {
			List<String> receipents = emailBean.getRecipients();
			Message message = new MimeMessage(getSession(properties));
			message.setFrom(new InternetAddress(appConfig.getMailConfig().getUsername()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.prepareRecipient(receipents)));
			message.setSubject(properties.getProperty("EMAIL_SUBJECT"));

			Multipart multipart = new MimeMultipart();
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(properties.getProperty("EMAIL_BODY"), "text/html; charset=utf-8");
			multipart.addBodyPart(messageBodyPart);
			if (emailBean.sizeOfAttachmentPath() > 0) {
				this.addAttachments(emailBean.getAttachmentPath(), message, multipart, EmailBean.ABSOLUTE_PATH);
			}
			if (emailBean.sizeOfAttachmentRelativePathToBaseFilePath() > 0) {
				this.addAttachments(emailBean.getAttachmentRelativePathToBaseFilePath(), message, multipart, EmailBean.RELATIVE_PATH_TO_BASE_FILE_PATH);
			}
			if (emailBean.sizeOfAttachmentRelativePathToWebAppPath() > 0) {
				this.addAttachments(emailBean.getAttachmentRelativePathToWebAppPath(), message, multipart, EmailBean.RELATIVE_PATH_TO_WEB_APP_PATH);
			}
			Transport.send(message);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/** ADDING ATTACHMENT TO EMAIL */
	private void addAttachments(List<String> attachments, Message message, Multipart multipart, Integer filePathType) throws MessagingException {
		for (int i = 0; i < attachments.size(); i++) {
			String attachmentPath = this.calculateAttachmentPath(attachments.get(i), filePathType);
			BodyPart attachmentBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(attachmentPath);
			attachmentBodyPart.setDataHandler(new DataHandler(source));
			attachmentBodyPart.setFileName(attachmentPath.substring(attachmentPath.lastIndexOf("/") + 1));
			multipart.addBodyPart(attachmentBodyPart);
		}
		message.setContent(multipart);
	}

	private String calculateAttachmentPath(String attachmentPath, Integer filePathType) {
		if (filePathType == EmailBean.RELATIVE_PATH_TO_BASE_FILE_PATH) {
			attachmentPath = appConfig.getPathConfig().getBasePath() + attachmentPath;
		} else if (filePathType == EmailBean.RELATIVE_PATH_TO_WEB_APP_PATH) {
			attachmentPath = appConfig.getPathConfig().getWebAppPath() + attachmentPath;
		}
		return attachmentPath;
	}

	private String prepareNotificationBodyByTemplate(NotificationTemplate template, String emailBody) throws Exception {
		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.init();
		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("object", template.getReferences());

		StringWriter writer = new StringWriter();
		velocityEngine.evaluate(velocityContext, writer, "", emailBody);
		return writer.toString();
	}

	private String prepareRecipient(List<String> receipents) {
		StringBuffer recipientList = new StringBuffer();
		recipientList.append(receipents.get(0));
		for (int i = 1; i < receipents.size(); i++) {
			recipientList.append("," + receipents.get(i));
		}
		return recipientList.toString();
	}

	/**
	 * @param smsBean
	 * @param template
	 * @return NotificationResponseBean
	 * @throws Exception
	 */
	public NotificationResponseBean sendSMS(SMSBean smsBean, NotificationTemplate template) throws Exception {
		boolean isSent = false;

		final SMSConfig smsConfig = smsConfigRepo.findById(smsBean.getConfigId());
		ArtAppNotificationTemplate notificationTemplate = artAppEmailTemplateRepository.findById(template.getTemplateId());
		String smsBody = prepareNotificationBodyByTemplate(template, notificationTemplate.getTemplate());
		SMSData smsData = new SMSData(smsBody, notificationTemplate.getTemplateAttributes());
		SMSNotificationBusinessService notificationBzService = (SMSNotificationBusinessService) applicationContext.getBean(smsConfig.getConfigurationBean());
		if (smsBean.getMobilesNos().size() > 1) {
			isSent = notificationBzService.sendSMS(smsConfig, smsData, smsBean.getMobilesNos());
		} else {
			isSent = notificationBzService.sendSMS(smsConfig, smsData, smsBean.getMobilesNos().get(0));
		}
		return new NotificationResponseBean(isSent);
	}
}
