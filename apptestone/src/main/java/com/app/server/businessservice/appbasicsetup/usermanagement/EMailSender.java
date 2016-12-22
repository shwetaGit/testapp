package com.app.server.businessservice.appbasicsetup.usermanagement;
import com.app.config.appSetup.model.AppConfiguration;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EMailSender {

	@Autowired
	private AppConfiguration appConfig;

	private Session getSession(Properties properties) {
		final javax.mail.Authenticator authenticator = new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(appConfig.getMailConfig().getUsername(), appConfig.getMailConfig().getPassword());
			}
		};
		return Session.getInstance(properties, authenticator);
	}

	public boolean sendMail(final String emailSubject, final String emailBody, final String receipent) throws Exception {
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

		final boolean emailStatus = sendEmail(properties, receipent);
		return emailStatus;
	}

	public boolean sendPassword(final String emailBody, final String receipent) throws Exception {
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

		properties.put("EMAIL_SUBJECT", "This email contains confidential information of user ");
		properties.put("EMAIL_BODY", emailBody);

		final boolean emailStatus = sendEmail(properties, receipent);
		return emailStatus;
	}

	/****
	 * METHOD FOR SENDING MAIL
	 */
	private boolean sendEmail(Properties properties, String receipent) {
		try {
			Message message = new MimeMessage(getSession(properties));
			message.setFrom(new InternetAddress(appConfig.getMailConfig().getUsername()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receipent));
			message.setSubject(properties.getProperty("EMAIL_SUBJECT"));
			message.setContent(properties.getProperty("EMAIL_BODY"), "text/html");
			Transport.send(message);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
