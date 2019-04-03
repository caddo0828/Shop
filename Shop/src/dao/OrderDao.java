package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import bean.Book;
import bean.Order;
import bean.User;
import service.DataHelper;
import service.MyCar;
import utils.DBCPUtils;

/**
 * ���������Ϣ���ɶ�Ӧ�Ķ�����orders�Լ������ӱ�orderitem
 * @author ����
 */
public class OrderDao {
	private static Connection conn = null;
	private static PreparedStatement prep = null;
	private static ResultSet rs = null;
	public static String lastOrderId = null;
	
	/**
	 * @param helper ���������࣬�������������ӽ�������
	 * @param user  �û��࣬���ڻ�ȡ�û���id�ţ������ڶ�Ӧ�����ݿ��orders��
	 * @param order ������Ϣ�࣬��ȡ�ջ����������ջ��绰���ջ���ַ���������ݿ��orders��
	 * @return �������ݿ����Ϣ�Ƿ�ɹ�����
	 */
	public static boolean saveOrder(DataHelper helper ,User user,Order order) {
		//��ȡ��ǰ�ύ������ʱ��
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = dateFormat.format(date);
		String sql = "insert into orders(id,userID,orderName,telephone,address,totalPrice,orderDate) values(?,?,?,?,?,?,?)";
		try {
			conn = new DBCPUtils().getConnection();
			//�������ݿ�Ϊ�ɲ���ִ�У����������ύ(TRANSACTION_SERIALIZABLE)���񲢷�ִ��
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			
			//������
			lastOrderId = UUID.randomUUID().toString();
			prep = conn.prepareStatement(sql);
			prep.setString(1, lastOrderId);
			prep.setInt(2, user.getId());
			prep.setString(3, order.getOrderName());
			prep.setString(4, order.getTel());
			prep.setString(5, order.getAddress());
			prep.setDouble(6, helper.getTotalPrice());
			prep.setString(7, time);
			prep.executeUpdate();
			
			sql = "insert into orderitem(id,orderID,bookID,bookNum) values(?,?,?,?)";
			ArrayList<Book> list = helper.findAllBook();
			for(Book book : list) {
				prep = conn.prepareStatement(sql);
				prep.setString(1, UUID.randomUUID().toString());
				prep.setString(2, lastOrderId);
				prep.setString(3, book.getId());
				prep.setInt(4, book.getShopNums());
				prep.executeUpdate();
			}
			
			//�����ύ
			conn.commit();
			return true;
		} catch (SQLException e) {
			//������ʧ��ʱ�ع�
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
			DBCPUtils.release(conn, prep, rs);
		}
	    return false;
	}
	
	/**
	 * ���ݶ����ţ��ҵ���Ӧ�Ķ���ʱ��,һ��Ҫ�����ɶ����ɹ���ִ��
	 * @param lastOrderId ������
	 * @return ����ʱ��
	 */
	public static String findOrderTime(String lastOrderId) {
		conn = new DBCPUtils().getConnection();
		String sql = "select * from orders where id=?";
		String orderTime = null;
		try {
			prep = conn.prepareStatement(sql);
			prep.setString(1, lastOrderId);
			rs = prep.executeQuery();
			if(rs.next()) {
				orderTime = rs.getString("orderDate");
			}
			return orderTime;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCPUtils.release(conn, prep, rs);
		}
		return orderTime;
	}
	
}
