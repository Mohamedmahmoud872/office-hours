package base;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class SendEmail 
{
      public void sendMail(String recipientEmail, String subject, String body) 
      {
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		String myAccountEmail = "notificationsys2020@gmail.com";
		String myAccountPass = "notification2020";
		Session session = Session.getInstance(properties, new Authenticator() 
                {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {return new PasswordAuthentication(myAccountEmail, myAccountPass);}
                });
		Message message = prepareMessage(session, myAccountEmail, recipientEmail, subject, body);
		try 
                {
			Transport.send(message);
		}
                catch (MessagingException e) 
                {
			e.printStackTrace();
		}
	}

	private static Message prepareMessage(Session session, String myAccountEmail, String recipientEmail, String subject,String body) 
        {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
			message.setSubject(subject);
			message.setText(body);
			return message;
		} 
                catch (AddressException e) 
                {
			e.printStackTrace();
		}
                catch (MessagingException e) {
		
			e.printStackTrace();
		}
		return null;
	}
}
