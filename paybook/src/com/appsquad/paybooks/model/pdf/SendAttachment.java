package com.appsquad.paybooks.model.pdf;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.appsquad.paybooks.bean.GeneratePayslipBean;

public class SendAttachment {
   
	public static void main(String[] args) {
		//generateAndSendEmailForApproveOrReject("prolayjitdutta@gmail.com", "status");
	}
	
	public static Boolean generateAndSendEmailForApproveOrReject(GeneratePayslipBean bean,String status,String filePath) {

		Properties mailServerProperties;
		Session getMailSession;
		String fromEmail = "appsquad.payslip@gmail.com", fromPassword = "appsquad2000";
		MimeMessage generateMailMessage;

		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.host", "smtp.gmail.com");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.user", fromEmail);
		mailServerProperties.put("mail.smtp.password", fromPassword);
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		System.out.println("Mail Server Properties have been setup successfully..");

		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		try {
			generateMailMessage.setFrom(new InternetAddress(fromEmail));
			generateMailMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(bean.getEmailId()));
			generateMailMessage.setSubject("Payslip of appsquad pvt ltd");

			DataSource source = new FileDataSource(filePath);  
			MimeBodyPart messageBodyPart2 = new MimeBodyPart(); 
			BodyPart messageBodyPart1 = new MimeBodyPart();  
			messageBodyPart1.setContent(bodyMessage(bean), "text/html");
			messageBodyPart2.setDataHandler(new DataHandler(source));  
			messageBodyPart2.setFileName("Payslip_"+bean.getMonth()+"_"+bean.getYear()+".pdf");  

			//5) create Multipart object and add MimeBodyPart objects to this object      
			Multipart multipart = new MimeMultipart();  
			multipart.addBodyPart(messageBodyPart1);  
			multipart.addBodyPart(messageBodyPart2);  

			//6) set the multiplart object to the message object  
			generateMailMessage.setContent(multipart );  

			//generateMailMessage.setContent(emailBody, "text/html");
			System.out.println("Mail Session has been created successfully..");

			//
			Transport transport = getMailSession.getTransport("smtp");
			transport.connect("smtp.gmail.com",fromEmail, fromPassword);
			transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
			System.out.println("Mail has been sent successfully..");
			transport.close();
			return true;
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static String bodyMessage(GeneratePayslipBean bean){
		StringBuilder sb = new StringBuilder();
		sb.append("<html>"
				+ "	<body>"
				+ "	<p>"
				+ "	Hello "+bean.getEmployeeName()+","
				+ "<br>"
				+ "Your Payslip for the month of "+bean.getMonth()+"-"+bean.getYear()+" is on the attachment."
				+ "</p>"
				+ "<br>"
				+ "Thanks.<br>"
				+ "Appsquad pvt ltd."
				+ "</body>"
				+ ""
				+ "</html>");
		return sb.toString();
	}
}
