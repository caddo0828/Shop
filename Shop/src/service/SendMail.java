package service;

import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
/**
 * 顶单成功生成后，根据用户的邮箱，发送确认订单的邮件
 * @author 老腰
 */
public class SendMail {
	
	public static Message createSimpleMail(Session session, String recipients)
			throws AddressException, MessagingException {
		// 创建邮件对象
		MimeMessage message = new MimeMessage(session);
		// 指名邮件的发送人邮箱地址
		message.setFrom(new InternetAddress("tan_qi@sohu.com"));
		// 指明邮件的收件人，
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
		// 设置邮件标题
		message.setSubject("订单确认");

		// 4、创建正文对象
		// 第一部分：HTML部分
		BodyPart text = new MimeBodyPart();
		//设置照片节点
		text.setContent("<img src='cid:car.jpg'> <br> <a href='http://localhost:8080/Shop/index.jsp'>点击此处登录,查看订单消息</a> ", "text/html;charset=utf-8");
		// 第二部分：img部分
		BodyPart image = new MimeBodyPart();
		// 加载附件对象(反射将编译的环境获取，在获取资源的相对路径)
		String path= SendMail.class.getResource("/").getPath();
		File f = new File(path+"car.jpg");
		
		
		DataHandler dataHandler = new DataHandler(new FileDataSource(f));
		image.setDataHandler(dataHandler);
	   	((MimeBodyPart)image).setContentID("car.jpg");
		//image.setHeader("Content-ID", "car.jpg");
		image.setFileName(dataHandler.getName());

		// 5、将正文对象加载入多媒体对象中
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(text); // 装载正文
		multipart.addBodyPart(image); // 装载图片内容
		// 邮件包含内嵌资源 （HTML中包含img标签）
		((MimeMultipart) multipart).setSubType("related");

		// 将多媒体对象加载入message对象中,设置邮件内容
		message.setContent(multipart);
		message.saveChanges();
		
		// 返回创建好的邮件对象
		return message;
	}
	
	public static void sendMail(String recipient) throws MessagingException {
		Properties prop = new Properties();
		//定义本机服务器地址
		prop.setProperty("mail.host", "localhost");
		//定义服务器协议
		prop.setProperty("mail.transport.protocol", "smtp");
		//定义用户权限,是否需要认证
		prop.setProperty("mail.smtp.auth", "true");
		//使用JavaMail发送邮件的5个步骤
		//1、创建session，用于建立网络连接的会话信息，如邮件服务器主机名，端口，并且创建transport,store对象
		Session session = Session.getInstance(prop);
		 //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
		 session.setDebug(true);
		 //2、通过session得到transport对象
		 try {
			Transport ts = session.getTransport();
			/*3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，
			发件人需要提交邮箱的用户名和密码给smtp服务器，
			用户名和密码都通过验证之后才能够正常发送邮件给收件人。
			*/
			ts.connect("smtp.sohu.com","tan_qi@sohu.com","tq6020162483");
			//4、创建邮箱
			Message message = createSimpleMail(session,recipient);
			//5、发送邮件 ,message 邮件，message.getAllRecipients() 收件人
			ts.sendMessage(message, message.getAllRecipients());
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
	}

}
