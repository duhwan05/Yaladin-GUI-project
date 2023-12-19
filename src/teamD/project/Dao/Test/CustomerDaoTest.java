package teamD.project.Dao.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import teamD.project.Dto.CustomerDto;
import teamD.project.Utility.OracleUtility;

public class CustomerDaoTest {
	//고객 회원가입
	public int insert(CustomerDto cust) throws SQLException {
		Connection conn = OracleUtility.getConnection();
		String sql = "insert into CUSTOMER_test values (cust_no_seq.nextval,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, cust.getCust_id());
		ps.setString(2, cust.getCust_name());
		ps.setInt(3, cust.getAge());
		ps.setString(4, cust.getAddress());
		ps.setString(5, cust.getTel());
		ps.setString(6, cust.getGender());
		int result = ps.executeUpdate();
		
		ps.close();
		conn.close();
		return result;
		
	}
	
	//로그인
	public CustomerDto select(String custid) throws SQLException{
	      Connection conn = OracleUtility.getConnection();
	    String sql="select * from customer_test where cust_id=?";
	    PreparedStatement ps= conn.prepareStatement(sql);
	    ps.setString(1,custid);
	    ResultSet rs=ps.executeQuery();

	    CustomerDto temp=null;
	    if(rs.next()) {
	       temp =new CustomerDto(rs.getInt(1),
	             rs.getString(2),
	             rs.getString(3),
	             rs.getInt(4),
	             rs.getString(5),
	             rs.getString(6),
	             rs.getString(7));
	    
	    }
	    ps.close();
	   conn.close();
	    
	    return temp;
	}
	
}

