package com.sunan.utils;

import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
	
	/*
	 * @Autowired private JavaMailSender javaMailSender;
	 * 
	 * public void sendEmail(String emailID, String text) {
	 * 
	 * try { SimpleMailMessage msg = new SimpleMailMessage(); msg.setTo(emailID);
	 * 
	 * msg.setSubject("Vision School ERP Credentials"); msg.setText(text);
	 * 
	 * javaMailSender.send(msg); } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * }
	 */
	
	public String getEmailMsg(String userName, String PWD) {
		return "Welcome, \nYou are in the process of signing in to your account.\nSchool ERP Credentials,\n"
        		+ "UserName: "+ userName +"\nPassword: "+ PWD +"\nTo protect your account, do not disclose it to anyone.";
	}
	

//	public static void main(String[] args) {
//
//        final String username = "visionschool.erp@gmail.com";
//        final String password = "school@erp123";
//
//        Properties prop = new Properties();
//		prop.put("mail.smtp.host", "smtp.gmail.com");
//        prop.put("mail.smtp.port", "465");
//        prop.put("mail.smtp.auth", "true");
//        prop.put("mail.smtp.socketFactory.port", "465");
//        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//
//        Session session = Session.getInstance(prop,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(username, password);
//                    }
//                });
//
//        try {
//
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("visionschool.erp@gmail.com"));
//            message.setRecipients(
//                    Message.RecipientType.TO,
//                    InternetAddress.parse("mane.nivas4@gmail.com")
//            );
//            message.setSubject("Testing Gmail SSL");
//            message.setText("Dear Mail Crawler,"
//                    + "\n\n Please do not spam my email!");
//
//            Transport.send(message);
//
//            System.out.println("Done");
//
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//    }
}
