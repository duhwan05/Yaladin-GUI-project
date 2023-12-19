package teamD.project.Dao.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import teamD.project.Dto.UsedBookDto;
import teamD.project.Utility.OracleUtility;

public class UsedBookDaoTest {
	/*private static UsedBookDaoTest dao = new UsedBookDaoTest();
    private UsedBookDaoTest() {}
    public static UsedBookDaoTest getUsedBookDao() {
       return dao;
    }*/
			// 전체상품 조회 Dao selectAll()
			public List<UsedBookDto> selectAll() throws SQLException{
				Connection conn = OracleUtility.getConnection();
				String sql = "select * from Used_book_test";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();

				List<UsedBookDto> list = new ArrayList<>();
				while(rs.next()) {
					list.add(new UsedBookDto(rs.getString(1), 
							rs.getInt(2), 
							rs.getString(3), 
							rs.getString(4), 
							rs.getString(5), 
							rs.getString(6), 
							rs.getInt(7),
					rs.getInt(8),
					//rs.getInt(9),
					rs.getDate(10)));
				}
				ps.close();
				conn.close();
				return list;
			}
//=================================================================================
			
			//책 이름별 조회 selectBybookname
			public List<UsedBookDto> selectBybookname(String book_name) throws SQLException{
				//bookname은 사용자가 입력한 검색어
				Connection conn = OracleUtility.getConnection();
				String sql = "select * from Used_Book_test "
						+ "where book_name like '%' || ? || '%'"; 
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, book_name);
				ResultSet rs = ps.executeQuery();

				List<UsedBookDto> list = new ArrayList<>();
				while(rs.next()) {
					list.add(new UsedBookDto(rs.getString(1), 
					rs.getInt(2), 
					rs.getString(3), 
					rs.getString(4), 
					rs.getString(5), 
					rs.getString(6), 
					rs.getInt(7),
			rs.getInt(8),
		//	rs.getInt(9),
			rs.getDate(10)));
				}
				return list;
			}
			
//========================================================================			
			//새로운 중고책 추가 addUsedBook
		public void addUsedBook(UsedBookDto usedBook) throws SQLException {
		        Connection conn = OracleUtility.getConnection();
		        String sql = "insert into used_book_test values (?,?,?,?,?,?,?,1,null,sysdate)";		//total은 책 추가할 때 필요없는 항목이어서 null 처리
		        PreparedStatement ps = conn.prepareStatement(sql);
		           
		            ps = conn.prepareStatement(sql);
		            ps.setString(1, usedBook.getUsed_book_code());		//나중에 book_code 시퀀스 변경예정 -- 랜덤 숫자로 넣는걸로 변경완료
		            ps.setInt(2, usedBook.getCust_no());
		            ps.setString(3, usedBook.getBook_name());
		            ps.setString(4, usedBook.getCategory());
		            ps.setString(5, usedBook.getAuthor());
		            ps.setString(6, usedBook.getPublisher());
		            ps.setInt(7, usedBook.getUsed_price());
		           // ps.setInt(8, usedBook.getCnt());		     
		          //  ps.setDate(9, usedBook.getSel_date());
		        
		            ps.executeUpdate();
		       
		    }


public List<UsedBookDto> selectByCategory(String category) throws SQLException{
	Connection conn = OracleUtility.getConnection();
	String sql = "select * from used_book_test where category like '%' || ? || '%'";
	PreparedStatement ps = conn.prepareStatement(sql);
	ps.setString(1, category);
	ResultSet rs = ps.executeQuery();
	
	List<UsedBookDto> list = new ArrayList<>();
	while(rs.next()) {
		list.add(new UsedBookDto(rs.getString(1), 
		rs.getInt(2), 
		rs.getString(3), 
		rs.getString(4), 
		rs.getString(5), 
		rs.getString(6), 
		rs.getInt(7),
rs.getInt(8),
//rs.getInt(9),
rs.getDate(10)));
	}
	return list;
	}


//중고책 삭제(used_book)
public int UBdelete(String ubcode) throws SQLException {
	Connection conn = OracleUtility.getConnection();
	String sql = "delete from used_book_test where used_book_code = ?";
	PreparedStatement ps = conn.prepareStatement(sql);
	ps.setString(1, ubcode);
	int result = ps.executeUpdate();
	ps.close();
	conn.close();
	return result;
}
}








//=============================================================================
//				***추후 사용될수도 있는 Dao****
/*		    // 추가된 항목 조회 (특정 회원이 추가한 책만 조회, 회원ID로 조회 -> 입력받은 회원 ID) 
			
		    public List<UsedBookDto> getAddedBooksByCustomer(String cust_id) throws SQLException{
		        Connection conn = OracleUtility.getConnection();
		        String sql = "select \r\n"
		        		+ "   used_book_code, book_name, category, publisher, author, used_price,cnt,sel_date\r\n"
		        		+ "from \r\n"
		        		+ "   used_book_test b1\r\n"
		        		+ "join\r\n"
		        		+ "   (\r\n"
		        		+ "   select *\r\n"
		        		+ "   from customer_test\r\n"
		        		+ "   where cust_id = ?\r\n"
		        		+ "   ) b2\r\n"
		        		+ "on \r\n"
		        		+ "   b1.cust_no = b2.cust_no\r\n"
		        		+ "order by used_book_code";
		        PreparedStatement ps = conn.prepareStatement(sql);
		        ps.setString(1, cust_id);
		        ResultSet rs = ps.executeQuery();
		        List<UsedBookDto> addedBooks = new ArrayList<>();

		            while (rs.next()) {
		                UsedBookDto usedBook = UsedBookDto.builder()
		                        .used_book_code(rs.getString("used_book_code"))
		                        .cust_no(rs.getInt("cust_no"))
		                        .book_name(rs.getString("book_name"))
		                        .category(rs.getString("category"))
		                        .publisher(rs.getString("publisher"))
		                        .author(rs.getString("author"))
		                        .used_price(rs.getInt("used_price"))
		                        .cnt(rs.getInt("cnt"))
		                        .sel_date(rs.getDate("sel_date"))
		                        .build();

		                addedBooks.add(usedBook);
		            }

		        return addedBooks;
		    }
		}
*/

