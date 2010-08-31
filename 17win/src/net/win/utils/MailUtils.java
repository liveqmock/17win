package net.win.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

	public static void sendRegisterMail(JavaMailSender mailSender,
			FreeMarkerConfigurer configurer, String username, String email)
			throws Exception {
		MimeMessage msg = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, false, "utf-8");
		helper.setFrom(Constant.FROM_EMAIL);
		helper.setTo(email);
		helper.setSubject(username + "：欢迎您注册www.17win.com(一起赢)平台。");
		HashMap map = new HashMap();
		map.put("content", "欢迎");
		String htmlText = getMailText(map, configurer, "register.ftl");
		helper.setText(htmlText, true);
		mailSender.send(msg);
	}

	/**
	 * 发送找密码的邮件
	 * 
	 * @param args
	 */

	public static void sendPasswordMail(JavaMailSender mailSender,
			FreeMarkerConfigurer configurer, String username, String email)
			throws Exception {
		// String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(
		// tpl, result);// 加入map到模板中 对应${content}
		// //
		// // mail.setFrom(Constant.FROM_EMAIL);
		// // mail.setTo(email);
		// // mail.setSubject(username + "欢迎您加入www.17win.com(一起赢)平台");
		// // mail.setText(htmlText);
		// //
		// // mailSender.send(mail);
		// MimeMessage msg = mailSender.createMimeMessage();
		// // false表示非marltipart,UTF-8为字符编码
		// MimeMessageHelper helper = new MimeMessageHelper(msg, false,
		// "UTF-8");
		// helper.setSubject(username + "欢迎您加入www.17win.com(一起赢)平台");
		// helper.setFrom(Constant.FROM_EMAIL);
		// helper.setTo(email);
		// helper.setText(htmlText, true);// 设置为true表示发送的是HTML
		// mailSender.send(msg);

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
