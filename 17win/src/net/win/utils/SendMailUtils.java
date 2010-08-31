package net.win.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
@SuppressWarnings({"unchecked", "unused"})
public final class SendMailUtils {
	private static Configuration cfg = new Configuration();
	static {
		try {
			cfg.setDirectoryForTemplateLoading(new File(winUtils
					.getClassesPath()));
		} catch (IOException e) {
			LoggerUtils.error(e);
		}
	}

	private SendMailUtils() {
	}
	/**
	 * 发送注册信息的邮件
	 * 
	 * @param args
	 */

	public static void sendRegisterMail(JavaMailSender mailSender,
			String username, String email) throws Exception {
		SimpleMailMessage mail = new SimpleMailMessage();
		Template tpl = cfg.getTemplate("register.ftl");

		Map result = new HashMap();
		result.put("content", "内容");
		String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(
				tpl, result);// 加入map到模板中 对应${content}

		//		
		// mail.setFrom(Constant.FROM_EMAIL);
		// mail.setTo(email);
		// mail.setSubject(username + "欢迎您加入www.17win.com(一起赢)平台");
		// mail.setText(htmlText);
		//
		// mailSender.send(mail);
		MimeMessage msg = mailSender.createMimeMessage();
		// false表示非marltipart,UTF-8为字符编码
		MimeMessageHelper helper = new MimeMessageHelper(msg, false, "UTF-8");
		helper.setSubject(username + "欢迎您加入www.17win.com(一起赢)平台");
		helper.setFrom(Constant.FROM_EMAIL);
		helper.setTo(email);
		helper.setText(htmlText, true);// 设置为true表示发送的是HTML
		mailSender.send(msg);

	}

}
