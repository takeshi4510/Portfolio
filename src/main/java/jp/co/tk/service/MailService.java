package jp.co.tk.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	private final MailSender mailSender;

	public MailService(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendMail(HttpServletRequest request) {
		SimpleMailMessage msg = new SimpleMailMessage();

		//送信者
		msg.setFrom("ntks1595@gamil.com");
		//宛先
		msg.setTo("nakasone.0840@gmail.com");
		//掲題
		msg.setSubject(request.getParameter("name"));
		//内容
		msg.setText(request.getParameter("message"));
		//送信
		mailSender.send(msg);
	}
}
