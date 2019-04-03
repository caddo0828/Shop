package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import bean.Book;
import utils.DBCPUtils;

/**
 * 业务逻辑类，用来处理书籍的业务
 * 对应数据库表books
 * @author 老腰
 */
public class BookDao {
	private static Connection conn = null;
	private static PreparedStatement prep = null;
	private static ResultSet rs = null;
	private static int pageSize = 12; //每页显示多少条数据
	//public static int pageNumber =1 ; //当前所处的页数，从客户段获取,默认为1，初始界面
	public  static int pageCount ;  //总页数
	private static int totalRecounds;  //数据库总记录
	private static int pages;//起始位置
	
	/**将数据库书籍进行分页处理
	 * @param pageNumber 当前的页数
	 * @return 对应页数的所有书籍集合
	 */
	public static ArrayList<Book> findBook(int pageNumber) {
		ArrayList<Book> list = new ArrayList<Book>();
		conn = new DBCPUtils().getConnection();
		String sql = "SELECT COUNT(*) from books;";
		try {
			//设置数据库执行操作为可并发执行
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			
			prep = conn.prepareStatement(sql);
			rs = prep.executeQuery();
			rs.next();
			//数据库书籍总记录数
			totalRecounds = rs.getInt(1);
			//算出共有多少页
			pageCount = (totalRecounds-1)/pageSize+1;
			
			//起始位置(数据库从几开始取值)
			pages = pageSize*(pageNumber-1);
			
			//分页查询处理
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
			
			//批量提交
			conn.commit();
			return list;
		} catch (SQLException e) {
			try {
				//事务操作失败，回滚到之前的状态
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
	 * 根据id号找到对应的书籍
	 * @param id  书籍id号
	 * @return 对应书籍
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
	 * 根据对应的书籍修改位于数据库中的书籍库存量
	 * @param book 要进行库存量修改的书籍
	 * @return 是否修改成功
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
