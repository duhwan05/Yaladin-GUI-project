package teamD.project.Dao.Test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import teamD.project.Dto.CartBookDto;
import teamD.project.Utility.OracleUtility;




public class CartBookDaoTest {
		//새책 추가
		public int Nbinsert(CartBookDto cb) throws SQLException {
			Connection conn = OracleUtility.getConnection();
			String sql = "insert into cart_book_test values (cart_no_seq.nextval,?,?,null,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cb.getCust_no());
			ps.setString(2, cb.getNew_book_code());
			ps.setInt(3, cb.getCnt());
			ps.setString(4, cb.getStatus());
			int result = ps.executeUpdate();
			
			ps.close();
			conn.close();
			return result;
		}
		/*//중고책 추가
		public int Ubinsert(CartBookDto cb) throws SQLException {
			Connection conn = OracleUtility.getConnection();
			String sql = "insert into cart_book_test values (cart_no_seq.nextval,?,null,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cb.getCust_no());
			ps.setString(2, cb.getUsed_book_code());
			ps.setInt(3, cb.getCnt());
			ps.setString(4, cb.getStatus());
			int result = ps.executeUpdate();
			ps.close();
			conn.close();
			return result;
		}*/
		
		
		// 프로시저
		public int ubpro(CartBookDto cb) throws SQLException {
			Connection conn = OracleUtility.getConnection();
			String sql = "{call used_book_cnt(?,?)}";
			CallableStatement ps = conn.prepareCall(sql);{
			ps.setInt(1,cb.getCust_no());
			ps.setString(2,cb.getUsed_book_code());
	
			
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
		
		
		
		
		
		
		public int nbpro(CartBookDto cb) throws SQLException {
			Connection conn = OracleUtility.getConnection();
			String sql = "{call new_book_cnt(?,?)}";
			CallableStatement ps = conn.prepareCall(sql);{
			ps.setInt(1,cb.getCust_no());
			ps.setString(2,cb.getNew_book_code());
			int result = ps.executeUpdate();
			ps.close();
			conn.close();
				 
			return result;
			}
			
		}
		
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
		
		
		//장바구니 삭제(new_book)
		public int delete(CartBookDto cb) throws SQLException {
			Connection conn = OracleUtility.getConnection();
			String sql = "delete from cart_book_test where new_book_code = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cb.getNew_book_code());
			int result = ps.executeUpdate();
			ps.close();
			conn.close();
			return result;
		}
		
		//장바구니 삭제(used_book)
		public int delete2(CartBookDto cb) throws SQLException {
			Connection conn = OracleUtility.getConnection();
			String sql = "delete from cart_book_test where used_book_code = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cb.getUsed_book_code());
			int result = ps.executeUpdate();
			ps.close();
			conn.close();
			return result;
		}
		
		
		//
/*	public List<CartBookDto> selectByCustno(int custno) throws SQLException{
		Connection conn = OracleUtility.getConnection();
	String sql = "SELECT book_name, author,price,cbt.cnt\r\n"
			+ "FROM NEW_BOOK_TEST nbt \r\n"
			+ "JOIN CART_BOOK_TEST cbt \r\n"
			+ "ON nbt.NEW_BOOK_CODE = cbt.NEW_BOOK_CODE\r\n"
			+ "WHERE cbt.CUST_NO = ?\r\n"
			+ "UNION ALL\r\n"
			+ "SELECT book_name, author,used_price,cbt2.cnt\r\n"
			+ "FROM used_BOOK_TEST ubt\r\n"
			+ "JOIN CART_BOOK_TEST cbt2\r\n"
			+ "ON ubt.used_BOOK_CODE = cbt2.used_BOOK_CODE\r\n"
			+ "WHERE cbt2.CUST_NO = ?";
	PreparedStatement ps = conn.prepareStatement(sql);
	ps.setInt(1, custno);
	ps.setInt(2, custno);
	
	List<CartBookDto> results = new ArrayList<>();
	ResultSet rs = ps.executeQuery();
	while(rs.next()) {
		CartBookDto cart = CartBookDto.builder()
				.book_name(rs.getString(1))
				.author(rs.getString(2))
				.price(rs.getInt(3))
				.cnt(rs.getInt(4)).build();
		results.add(cart);
	}
	
	return results;
			
	}*/

		//장바구니 특정 책코드 조회
		public CartBookDto selectCartBookByCode(int custno , String ub_code) throws SQLException {
			Connection conn = OracleUtility.getConnection();
			String sql = "select * From cart_book_test where cust_no = ? and new_book_code = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, custno);
			ps.setString(2, ub_code);
			
			CartBookDto result =null;
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				result = CartBookDto.builder().cart_no(rs.getInt(1)).cust_no(rs.getInt(2)).new_book_code(rs.getString(3))
						.used_book_code(rs.getString(4)).cnt(rs.getInt(5)).status(rs.getString(6)).build();
			}
			
			return result;
		}
		
		//장바구니에 있는 used_code 카운트 증가 업데이트
		public int updateCartCount(int custno , String ub_code) throws SQLException {
		    Connection conn = OracleUtility.getConnection();
		    String sql = "update cart_book_test set cnt=cnt+1  where cust_no = ? and new_book_code = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, custno);
			ps.setString(2, ub_code);
			
			int result = ps.executeUpdate();
		    ps.close();
		    conn.close();
		    return result;
		}
		
		public int minusCartCount(int custno , String nb_code) throws SQLException {
		    Connection conn = OracleUtility.getConnection();
		    String sql = "update cart_book_test set cnt=cnt-1  where cust_no = ? and new_book_code = ? or used_book_code =?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, custno);
			ps.setString(2, nb_code);
			ps.setString(3, nb_code);
			
			int result = ps.executeUpdate();
		    ps.close();
		    conn.close();
		    return result;
		}
		
		
		
		
		//내역조회
		public List<CartBookDto> selectDB(int custno) throws SQLException {
			Connection conn = OracleUtility.getConnection();
			String sql = "select * From cart_book_test where cust_no = ? and status = 'n'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, custno);
			
			List<CartBookDto> results = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				CartBookDto cart = CartBookDto.builder().cart_no(rs.getInt(1)).cust_no(rs.getInt(2)).new_book_code(rs.getString(3))
						.used_book_code(rs.getString(4)).cnt(rs.getInt(5)).status(rs.getString(6)).build();
				results.add(cart);
			}
			
			return results;
		}
		
		
		
//새로만든 list 형식 Dao
	public int UbinsertList(List<CartBookDto> cartBooks) throws SQLException {
	    Connection conn = OracleUtility.getConnection();
	    String sql = "insert into cart_book values (?,?,null,?,?,?)";
	    PreparedStatement ps = conn.prepareStatement(sql);
	    int result = 0;

	    for (CartBookDto cb : cartBooks) {
	        ps.setInt(1, cb.getCart_no());
	        ps.setInt(2, cb.getCust_no());
	        ps.setString(3, cb.getBook_name());
	        ps.setString(4, cb.getAuthor());
	        ps.setInt(5, cb.getPrice());
	        ps.setString(6, cb.getNew_book_code());
	        ps.setString(7, cb.getUsed_book_code());
	        ps.setInt(8, cb.getCnt());
	        ps.setString(9, cb.getStatus());
	        result += ps.executeUpdate();
	    }

	    ps.close();
	    conn.close();
	    return result;
	}
	

	
	

}	
		
