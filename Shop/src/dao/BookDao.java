package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import bean.Book;
import utils.DBCPUtils;

/**
 * ҵ���߼��࣬���������鼮��ҵ��
 * ��Ӧ���ݿ��books
 * @author ����
 */
public class BookDao {
	private static Connection conn = null;
	private static PreparedStatement prep = null;
	private static ResultSet rs = null;
	private static int pageSize = 12; //ÿҳ��ʾ����������
	//public static int pageNumber =1 ; //��ǰ������ҳ�����ӿͻ��λ�ȡ,Ĭ��Ϊ1����ʼ����
	public  static int pageCount ;  //��ҳ��
	private static int totalRecounds;  //���ݿ��ܼ�¼
	private static int pages;//��ʼλ��
	
	/**�����ݿ��鼮���з�ҳ����
	 * @param pageNumber ��ǰ��ҳ��
	 * @return ��Ӧҳ���������鼮����
	 */
	public static ArrayList<Book> findBook(int pageNumber) {
		ArrayList<Book> list = new ArrayList<Book>();
		conn = new DBCPUtils().getConnection();
		String sql = "SELECT COUNT(*) from books;";
		try {
			//�������ݿ�ִ�в���Ϊ�ɲ���ִ��
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			
			prep = conn.prepareStatement(sql);
			rs = prep.executeQuery();
			rs.next();
			//���ݿ��鼮�ܼ�¼��
			totalRecounds = rs.getInt(1);
			//������ж���ҳ
			pageCount = (totalRecounds-1)/pageSize+1;
			
			//��ʼλ��(���ݿ�Ӽ���ʼȡֵ)
			pages = pageSize*(pageNumber-1);
			
			//��ҳ��ѯ����
			//select * from userlist  order by userId limit n,m
			sql ="select * from books order by id limit"+" "+pages+","+pageSize;
			prep = conn.prepareStatement(sql);
			rs = prep.executeQuery();
			while(rs.next()) {
				Book book = new Book();
				book.setId(rs.getString("id"));
				book.setName(rs.getString("name"));
				book.setPrice(rs.getDouble("price"));
				book.setCategory(rs.getString("category"));
				book.setNums(rs.getInt("nums"));
				book.setImgurl(rs.getString("imgurl"));
				book.setDescription(rs.getString("description"));
				book.setAuthor(rs.getString("author"));
				list.add(book);		
			}
			
			//�����ύ
			conn.commit();
			return list;
		} catch (SQLException e) {
			try {
				//�������ʧ�ܣ��ع���֮ǰ��״̬
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			DBCPUtils.release(conn, prep, rs);
		}
		
		return null;
	}
	
	/**
	 * ����id���ҵ���Ӧ���鼮
	 * @param id  �鼮id��
	 * @return ��Ӧ�鼮
	 */
	public static Book findById(String id) {
		conn = new DBCPUtils().getConnection();
		String sql = "select * from books where id=?";
		try {
			prep = conn.prepareStatement(sql);
			prep.setString(1, id);
			rs = prep.executeQuery();
			while(rs.next()) {
				Book book = new Book();
				book.setId(rs.getString("id"));
				book.setName(rs.getString("name"));
				book.setPrice(rs.getDouble("price"));
				book.setCategory(rs.getString("category"));
				book.setNums(rs.getInt("nums"));
				book.setImgurl(rs.getString("imgurl"));
				book.setDescription(rs.getString("description"));
				book.setAuthor(rs.getString("author"));
				return book;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCPUtils.release(conn, prep, rs);
		}
		return null;
	}
	
	/**
	 * ���ݶ�Ӧ���鼮�޸�λ�����ݿ��е��鼮�����
	 * @param book Ҫ���п�����޸ĵ��鼮
	 * @return �Ƿ��޸ĳɹ�
	 */
	public static boolean updateNums(Book book) {
		conn = new DBCPUtils().getConnection();
		String sql = "update books set nums=? where id=?";
		try {
			prep = conn.prepareStatement(sql);
			prep.setInt(1, book.getNums());
			prep.setString(2, book.getId());
			int i = prep.executeUpdate();
			if(i>=1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCPUtils.release(conn, prep, rs);
		}
		return false;
	}
	

}
