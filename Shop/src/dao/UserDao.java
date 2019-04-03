package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.User;
import utils.DBCPUtils;

/**
 * 业务逻辑类，用来处理用户数据库的业务逻辑
 * 对应数据库为users
 * @author 老腰
 */
public class UserDao {
	private static Connection conn = null;
	private static PreparedStatement prep = null;
	private static ResultSet rs = null;
	
	/**
	 * 检验注册，插入数据,通过创建的用户插入数据
	 * @param user 注册的用户信息
	 * @return 是否注册成功
	 */
	public static boolean insert(User user) {
		conn = new DBCPUtils().getConnection();
		String sql = "insert into users(name,pwd,email,tel) values(?,?,?,?)";
		try {
			prep = conn.prepareStatement(sql);
			prep.setString(1, user.getName());
			prep.setString(2, user.getPwd());
			prep.setString(3, user.getEmail());
			prep.setString(4, user.getTel());
			int num = prep.executeUpdate();
			if(num>=1) {
				return true;
			}
			return false;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			//释放资源
			DBCPUtils.release(conn, prep, rs);
		}
		return false;
	}

	/**
	 * 根据用户电话号码和密码获取用户
	 * @param name 用户名
	 * @param pwd  用户密码
	 * @return 用户是否存在
	 */
	public static User search(String name,String pwd) {
		conn = new DBCPUtils().getConnection();
		String sql = "select * from users where name=? and pwd=?";
		try {
			prep = conn.prepareStatement(sql);
			//为？通配符赋值
			prep.setString(1, name);
			prep.setString(2, pwd);
			rs = prep.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPwd(rs.getString("pwd"));
				user.setTel(rs.getString("tel"));
				user.setEmail(rs.getString("email"));
				user.setGrade(rs.getInt("grade"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCPUtils.release(conn, prep, rs);
		}
		return null;
	}
	
	/**
	 * 根据用户名判断用户是否存在，如果存在，表明用户账号名已经被注册，（用户名为第二主键）
	 * @param name 用户注册名
	 * @return 用户名是否已经注册
	 */
	public static boolean findByName(String name) {
		conn = new DBCPUtils().getConnection();
		String sql = "select * from users where name=?";
		try {
			prep = conn.prepareStatement(sql);
			prep.setString(1, name);
			rs = prep.executeQuery();
			if(rs.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCPUtils.release(conn, prep, rs);
		}
		return false;
	}

	/**
	 * 根据用户注册邮箱号判断用户是否存在，如果存在，表明用户邮箱已经被注册
	 * @param email 注册邮箱号
	 * @return 注册邮箱是否存在
	 */
	public static boolean findByEmail(String email) {
		conn = new DBCPUtils().getConnection();
		String sql = "select * from users where email=?";
		try {
			prep = conn.prepareStatement(sql);
			prep.setString(1, email);
			rs = prep.executeQuery();
			if(rs.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCPUtils.release(conn, prep, rs);
		}
		return false;
	}
	
	/**
	 * 根据用户注册邮箱号判断用户是否存在，如果存在，表明用户邮箱已经被注册
	 * @param tel 用户注册电话
	 * @return 电话号码是否已经注册存在
	 */
	public static boolean findByTel(String tel) {
		conn = new DBCPUtils().getConnection();
		String sql = "select * from users where tel=?";
		try {
			prep = conn.prepareStatement(sql);
			prep.setString(1 , tel);
			rs = prep.executeQuery();
			if(rs.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCPUtils.release(conn, prep, rs);
		}
		return false;
	}
}

