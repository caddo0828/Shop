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
 * �����ɹ����ɺ󣬸����û������䣬����ȷ�϶������ʼ�
 * @author ����
 */
public class SendMail {
	
	public static Message createSimpleMail(Session session, String recipients)
			throws AddressException, MessagingException {
		// �����ʼ�����
		MimeMessage message = new MimeMessage(session);
		// ָ���ʼ��ķ����������ַ
		message.setFrom(new InternetAddress("tan_qi@sohu.com"));
		// ָ���ʼ����ռ��ˣ�
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
		// �����ʼ�����
		message.setSubject("����ȷ��");

		// 4���������Ķ���
		// ��һ���֣�HTML����
		BodyPart text = new MimeBodyPart();
		//������Ƭ�ڵ�
		text.setContent("<img src='cid:car.jpg'> <br> <a href='http://localhost:8080/Shop/index.jsp'>����˴���¼,�鿴������Ϣ</a> ", "text/html;charset=utf-8");
		// �ڶ����֣�img����
		BodyPart image = new MimeBodyPart();
		// ���ظ�������(���佫����Ļ�����ȡ���ڻ�ȡ��Դ�����·��)
		String path= SendMail.class.getResource("/").getPath();
		File f = new File(path+"car.jpg");
		
		
		DataHandler dataHandler = new DataHandler(new FileDataSource(f));
		image.setDataHandler(dataHandler);
	   	((MimeBodyPart)image).setContentID("car.jpg");
		//image.setHeader("Content-ID", "car.jpg");
		image.setFileName(dataHandler.getName());

		// 5�������Ķ���������ý�������
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(text); // װ������
		multipart.addBodyPart(image); // װ��ͼƬ����
		// �ʼ�������Ƕ��Դ ��HTML�а���img��ǩ��
		((MimeMultipart) multipart).setSubType("related");

		// ����ý����������message������,�����ʼ�����
		message.setContent(multipart);
		message.saveChanges();
		
		// ���ش����õ��ʼ�����
		return message;
	}
	
	public static void sendMail(String recipient) throws MessagingException {
		Properties prop = new Properties();
		//���屾����������ַ
		prop.setProperty("mail.host", "localhost");
		//���������Э��
		prop.setProperty("mail.transport.protocol", "smtp");
		//�����û�Ȩ��,�Ƿ���Ҫ��֤
		prop.setProperty("mail.smtp.auth", "true");
		//ʹ��JavaMail�����ʼ���5������
		//1������session�����ڽ����������ӵĻỰ��Ϣ�����ʼ����������������˿ڣ����Ҵ���transport,store����
		Session session = Session.getInstance(prop);
		 //����Session��debugģʽ�������Ϳ��Բ鿴��������Email������״̬
		 session.setDebug(true);
		 //2��ͨ��session�õ�transport����
		 try {
			Transport ts = session.getTransport();
			/*3��ʹ��������û��������������ʼ��������������ʼ�ʱ��
			��������Ҫ�ύ������û����������smtp��������
			�û��������붼ͨ����֤֮����ܹ����������ʼ����ռ��ˡ�
			*/
			ts.connect("smtp.sohu.com","tan_qi@sohu.com","tq6020162483");
			//4����������
			Message message = createSimpleMail(session,recipient);
			//5�������ʼ� ,message �ʼ���message.getAllRecipients() �ռ���
			ts.sendMessage(message, message.getAllRecipients());
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
	}

}
