package teamD.project.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// select 기능 테스트하는 클래스 부터는 지금 만든 유틸리티 클래스로 Connection 만들어줍니다.
public class OracleUtility {	// Connection 생성하여 제공해주고, 리소스 해제 메소드를 작성

	public static Connection getConnection() {
		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
//		String driver = "oracle.jdbc.driver.OracleDriver";
		String user = "iclass";
		String password = "0419";
		
		try {
//			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,password);			
		}catch(SQLException e) {
			System.out.println("데이터 베이스 연결 및 사용에 문제가 생겼습니다. "+e.getMessage());
		}
		return conn;	// 생성된 Connection 객체 리턴
	}// getConncection() 메소드
	
	public static void close(Connection conn) {
		try {
			if(conn != null) {
				conn.close();
				System.out.println("연결 종료합니다.");
			}else {
				System.out.println("Connection 이 NULL 입니다.");
			}
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 종류 오류 : "+e.getMessage());
		}
	}// close() 메소드
	
	
	
	
}// OracleUtility