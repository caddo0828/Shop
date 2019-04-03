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
 * 将购物的信息生成对应的订单表orders以及订单子表orderitem
 * @author 老腰
 */
public class OrderDao {
	private static Connection conn = null;
	private static PreparedStatement prep = null;
	private static ResultSet rs = null;
	public static String lastOrderId = null;
	
	/**
	 * @param helper 订单辅助类，将购买的数据添加进订单中
	 * @param user  用户类，用于获取用户的id号，保存在对应的数据库表orders中
	 * @param order 订单信息类，获取收货人姓名，收货电话，收货地址，插入数据库表orders中
	 * @return 代表数据库表信息是否成功插入
	 */
	public static boolean saveOrder(DataHelper helper ,User user,Order order) {
		//获取当前提交订单的时间
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = dateFormat.format(date);
		String sql = "insert into orders(id,userID,orderName,telephone,address,totalPrice,orderDate) values(?,?,?,?,?,?,?)";
		try {
			conn = new DBCPUtils().getConnection();
			//设置数据库为可并发执行，不可批量提交(TRANSACTION_SERIALIZABLE)事务并发执行
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			
			//订单号
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
			
			//批量提交
			conn.commit();
			return true;
		} catch (SQLException e) {
			//当操作失败时回滚
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			//释放资源
			DBCPUtils.release(conn, prep, rs);
		}
	    return false;
	}
	
	/**
	 * 根据订单号，找到对应的订单时间,一定要在生成订单成功后，执行
	 * @param lastOrderId 订单号
	 * @return 订单时间
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
