package com.nt.service;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service("sms")
public class ShoppingMgmtServiceImpl implements IShoppingService {

	@Autowired
	private JavaMailSender sender;

	@Value("${spring.mail.username}")
	private String fromEmail;

	@Override
	public String purchase(String[] items, double[] prices, String[] toMialIds) throws Exception {
		// calculate billamt
		double billamt = 0.0;
		for (double p : prices)
			billamt = billamt + p;
		// prepare message to send
		String msg = Arrays.toString(items) + " are purcheses having prices ::" + Arrays.toString(prices)
				+ " with bill amount::" + billamt;
		// trigger the email message
		String result = triggerEmail(msg, toMialIds);
		return msg + "..." + result;
	}

	private String triggerEmail(String msg, String[] toMialIds) throws Exception {
		// prepare email message
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		// set header values
		helper.setFrom(fromEmail);
		helper.setTo(toMialIds);
		helper.setSubject("Open it to know it");
		helper.setSentDate(new Date());
		// set body content
		helper.setText(msg);
		helper.addAttachment("nit.jpg", new ClassPathResource("nit.jpg"));
		// send the email message
		sender.send(message);
		return "email message is sent";
	}

}
