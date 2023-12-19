package teamD.project.Dao.Test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import teamD.project.Dto.BuyBookDto;
import teamD.project.Dto.CartBookDto;
import teamD.project.Utility.OracleUtility;

public class BuyBookDaoTest {

	/*
	// 장바구니 목록을 구매 테이블로 insert하는 구매 매소드
	   public int insertBuyBook(CartBookDto cart) throws SQLException {
	      Connection conn = OracleUtility.getConnection();
	      String sql = "INSERT INTO BUY_BOOK_TEST values(to_char(sysdate,'YYYYMMDDHH24MISS'),?,?,sysdate)";
	      PreparedStatement ps = conn.prepareStatement(sql);

	      ps.setInt(1, cart.getCart_no());
	      ps.setInt(2, cart.getPrice() * cart.getCnt());

	      int result = ps.executeUpdate();

	      ps.close();
	      conn.close();
	      return result;

	   }// insertBuyBook
	*/
	   
	
//// buyno 구하는 다오
public String insertBuyno(List<CartBookDto> list)  {
	//자바로 to_char(sysdate,'yyyymmddhhmi') 를 여기서 구하고 그값을 insert 할때 쓰고 리턴값으로 전달
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    String buyno = sdf.format( new Date());

	Connection conn = OracleUtility.getConnection();
	String sql = "INSERT INTO BUY_BOOK_TEST values(?,?,?,sysdate)";
	PreparedStatement ps;
	
	try {
		conn.setAutoCommit(false);
		ps = conn.prepareStatement(sql);
		
		for(CartBookDto cart : list) {
			ps.setString(1, buyno);
			ps.setInt(2,cart.getCart_no());
			ps.setInt(3, cart.getPrice()*cart.getCnt());
			ps.executeUpdate();
			nbpro2(cart);
			ubpro2(cart);
		}
		conn.commit();
		ps.close();
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	
		try {
			conn.rollback();
		} catch (SQLException e1) {
		}
	}
	return buyno; 

//	String buy_no = dao.insetBuyBook(cart);
//	selectbuy(buy_no)
	
}// insertBuyBook


// 구매내역 조회 메소드	
public List<BuyBookDto> selectAllBuyBook() throws SQLException{
	Connection conn = OracleUtility.getConnection();
	String sql = "select * from BUY_BOOK_TEST";
	PreparedStatement ps = conn.prepareStatement(sql);
	ResultSet rs = ps.executeQuery();
	List<BuyBookDto> results = new ArrayList<>();
	while(rs.next()) {
		BuyBookDto bbd = BuyBookDto.builder()
				.buy_no(rs.getString(1))
				.cart_no(rs.getInt(2))
				.total(rs.getInt(3))
				.order_date(rs.getDate(4)).build();
		results.add(bbd);
	}
	return results;
}


//회원별 구매내역 조회 메소드	
public List<BuyBookDto> selectBuyBook(int custno) throws SQLException{
	Connection conn = OracleUtility.getConnection();
	String sql = "SELECT buy_no,bb.CART_NO ,total,order_date\r\n"
			+ "FROM BUY_BOOK_TEST bb\r\n"
			+ "JOIN (SELECT *\r\n"
			+ "FROM CART_BOOK_TEST \r\n"
			+ "WHERE cust_no=?) cb\r\n"
			+ "ON bb.cart_no = cb.CART_NO";
	PreparedStatement ps = conn.prepareStatement(sql);
	ps.setInt(1, custno);
	ResultSet rs = ps.executeQuery();
	List<BuyBookDto> results = new ArrayList<>();
	while(rs.next()) {
		BuyBookDto bbd = BuyBookDto.builder()
				.buy_no(rs.getString(1))
				.cart_no(rs.getInt(2))
				.total(rs.getInt(3))
				.order_date(rs.getDate(4)).build();
		results.add(bbd);
	}
	return results;
}


	//buy_no로 select
//	public List<BuyBookDto> selectbuyno(String buy_no) throws SQLException {
//
//		Connection conn = OracleUtility.getConnection();
//		String sql = "select * From buy_book_test where buy_no = ?";
//		PreparedStatement ps = conn.prepareStatement(sql);
//		ps.setString(1, buy_no);
//
//		List<BuyBookDto> result = new ArrayList<>();
//		ResultSet rs = ps.executeQuery();
//		while(rs.next()) {
//			BuyBookDto dto = BuyBookDto.builder()
//					.buy_no(rs.getString(1))
//					.cart_no(rs.getInt(2))
//					.total(rs.getInt(3))
//					.order_date(rs.getDate(4)).build();
//			result.add(dto);
//		}
//		
//		return result;
//}
	public int nbpro2(CartBookDto cb) throws SQLException {
		Connection conn = OracleUtility.getConnection();
		String sql = "{call add_cart_new_book_cnt(?,?,?)}";
		CallableStatement ps = conn.prepareCall(sql);{
		ps.setInt(1,cb.getCust_no());
		ps.setString(2,cb.getNew_book_code());
		ps.setInt(3, cb.getCnt());
		int result = ps.executeUpdate();
		ps.close();
		conn.close();
			 
		return result;
		}
	
	}
	public int ubpro2(CartBookDto cb) throws SQLException {
		Connection conn = OracleUtility.getConnection();
		String sql = "{call add_cart_used_book_cnt(?,?,?)}";
		CallableStatement ps = conn.prepareCall(sql);{
		ps.setInt(1,cb.getCust_no());
		ps.setString(2,cb.getUsed_book_code());
		ps.setInt(3, cb.getCnt());
		int result = ps.executeUpdate();
		ps.close();
		conn.close();
			 
		return result;
		}
		
	}
	
	
	
}