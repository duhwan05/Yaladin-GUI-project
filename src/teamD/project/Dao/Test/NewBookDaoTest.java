package teamD.project.Dao.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import teamD.project.Dto.NewBookDto;
import teamD.project.Utility.OracleUtility;

public class NewBookDaoTest {

	//전체 조회(new_book)
	public List<NewBookDto> selectAll() throws SQLException{
		Connection conn = OracleUtility.getConnection();
		String sql = "select * from new_book_test";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		List<NewBookDto> list = new ArrayList<>();
		while(rs.next()) {
			list.add(new NewBookDto(rs.getString(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getString(5),
					rs.getInt(6),
					rs.getInt(7)));
		}
		ps.close();
		conn.close();
		return list;
	}
	
	//이름별 조회
	public List<NewBookDto> selectByBookName(String book_name) throws SQLException{
		Connection conn = OracleUtility.getConnection();
		String sql = "select * from new_book_test where book_name like '%' || ? || '%'";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, book_name);
		ResultSet rs = ps.executeQuery();
		
		List<NewBookDto> list = new ArrayList<>();
		while(rs.next()) {
			list.add(new NewBookDto(rs.getString(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getString(5),
					rs.getInt(6),
					rs.getInt(7)));
		}
		ps.close();
		conn.close();
		return list;
	}
	public List<NewBookDto> selectByCategory(String category) throws SQLException{
		Connection conn = OracleUtility.getConnection();
		String sql = "select * from new_book_test where category like '%' || ? || '%'";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, category);
		ResultSet rs = ps.executeQuery();
		
		List<NewBookDto> list = new ArrayList<>();
		while(rs.next()) {
			list.add(new NewBookDto(rs.getString(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getString(5),
					rs.getInt(6),
					rs.getInt(7)));
		}
		ps.close();
		conn.close();
		return list;
}
}

