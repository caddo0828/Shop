package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.User;
import utils.DBCPUtils;

/**
 * ҵ���߼��࣬���������û����ݿ��ҵ���߼�
 * ��Ӧ���ݿ�Ϊusers
 * @author ����
 */
public class UserDao {
	private static Connection conn = null;
	private static PreparedStatement prep = null;
	private static ResultSet rs = null;
	
	/**
	 * ����ע�ᣬ��������,ͨ���������û���������
	 * @param user ע����û���Ϣ
	 * @return �Ƿ�ע��ɹ�
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
			//�ͷ���Դ
			DBCPUtils.release(conn, prep, rs);
		}
		return false;
	}

	/**
	 * �����û��绰����������ȡ�û�
	 * @param name �û���
	 * @param pwd  �û�����
	 * @return �û��Ƿ����
	 */
	public static User search(String name,String pwd) {
		conn = new DBCPUtils().getConnection();
		String sql = "select * from users where name=? and pwd=?";
		try {
			prep = conn.prepareStatement(sql);
			//Ϊ��ͨ�����ֵ
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
	 * �����û����ж��û��Ƿ���ڣ�������ڣ������û��˺����Ѿ���ע�ᣬ���û���Ϊ�ڶ�������
	 * @param name �û�ע����
	 * @return �û����Ƿ��Ѿ�ע��
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
	 * �����û�ע��������ж��û��Ƿ���ڣ�������ڣ������û������Ѿ���ע��
	 * @param email ע�������
	 * @return ע�������Ƿ����
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
	 * �����û�ע��������ж��û��Ƿ���ڣ�������ڣ������û������Ѿ���ע��
	 * @param tel �û�ע��绰
	 * @return �绰�����Ƿ��Ѿ�ע�����
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

