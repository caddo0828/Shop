package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

/**
 * ���ݿ��������
 * @author ����
 */
public class DBCPUtils {
	//������Դ����
	private static DataSource ds = null;
	
	//����ȡ��Դʱ���ھ�̬������У�ֱ��������ļ��ض����أ�ֱ�Ӷ�ȡ��Ϣ
	static {
		Properties prop = new Properties();
		InputStream in = DBCPUtils.class.getClassLoader().getResourceAsStream("db.properties");
		try {
			prop.load(in);
			try {
				ds = BasicDataSourceFactory.createDataSource(prop);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//��ȡ���Ӷ���
	public Connection getConnection() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//�ͷ���Դ
	public static void release(Connection conn,PreparedStatement prep , ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(prep!=null) {
			try {
				prep.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
