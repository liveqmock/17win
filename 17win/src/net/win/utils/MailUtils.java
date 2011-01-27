package net.win.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Configuration;
import freemarker.template.Template;

@SuppressWarnings( { "unchecked", "unused" })
public final class MailUtils {
	private static Configuration cfg = new Configuration();
	static {
		try {
			cfg.setDirectoryForTemplateLoading(new File(ContextUtils
					.getClassesPath()
					+ File.separator + "mailTemplate"));
			cfg.setEncoding(Locale.CHINA, "GBK");
		} catch (IOException e) {
			LoggerUtils.error(e);
		}
	}

	private MailUtils() {
	}

	/**
	 * 发送注册信息的邮件
	 * 
	 * @param args
	 */

	public static void sendCommonMail(JavaMailSender mailSender,
			FreeMarkerConfigurer configurer, String subject, String content,
			String email) throws Exception {
		try {
			JavaMailSenderImpl javaMailSenderImpl = changeMailSender(mailSender);

			MimeMessage msg = javaMailSenderImpl.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(msg, false,
					"utf-8");
			helper.setFrom(Constant.getWinEmail());
			helper.setTo(email);
			helper.setSubject(subject);
			HashMap map = new HashMap();
			map.put("content", content);
			String htmlText = getMailText(map, configurer, "common.ftl");
			helper.setText(htmlText, true);
			mailSender.send(msg);
		} catch (Exception e) {
			// ingore
		}
	}

	private static JavaMailSenderImpl changeMailSender(JavaMailSender mailSender) {
		JavaMailSenderImpl javaMailSenderImpl = (JavaMailSenderImpl) mailSender;
		javaMailSenderImpl.setPassword(Constant.getWinEmailPassword());
		javaMailSenderImpl.setUsername(Constant.getWinEmailUsername());
		javaMailSenderImpl.setHost(Constant.getWinEmailHost());
		javaMailSenderImpl.setPort(Constant.getWinEmailPort());
		return javaMailSenderImpl;
	}

	/**
	 * 发送找密码的邮件
	 * 
	 * @param args
	 */

	public static void sendPasswordMail(JavaMailSender mailSender,
			FreeMarkerConfigurer configurer, String username, String email,
			String content) throws Exception {
		try {
			JavaMailSenderImpl javaMailSenderImpl = changeMailSender(mailSender);
			MimeMessage msg = javaMailSenderImpl.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, false,
					"utf-8");
			helper.setFrom(Constant.getWinEmail());
			helper.setTo(email);
			helper.setSubject(username + ":www.17win.com(一起赢)密码找回系统");
			HashMap map = new HashMap();
			map.put("content", content);
			String htmlText = getMailText(map, configurer, "findPassword.ftl");
			helper.setText(htmlText, true);
			mailSender.send(msg);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @param map
	 * @param freeMarkerConfigurer
	 * @return
	 * @throws Exception
	 */
	private static String getMailText(Map map,
			FreeMarkerConfigurer freeMarkerConfigurer, String template)
			throws Exception {
		Template tpl = freeMarkerConfigurer.getConfiguration().getTemplate(
				template);
		return FreeMarkerTemplateUtils.processTemplateIntoString(tpl, map);
	}

}
