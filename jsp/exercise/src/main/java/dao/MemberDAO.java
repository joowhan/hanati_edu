package dao;

import java.sql.SQLException;

import dto.MemberDTO;

public class MemberDAO extends JdbcConnection {

	public void signUp(MemberDTO memberDTO) {
		String sql = "INSERT INTO JSP_USER VAULES(?,?,?,?,?,?,?)";

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(0, memberDTO.getName());
			pstmt.setString(1, memberDTO.getId());
			pstmt.setString(2, memberDTO.getPassword());
			pstmt.setString(3, memberDTO.getPhone());
			pstmt.setString(4, memberDTO.getEmail());
			pstmt.setString(5, "승인 전");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean getPassword(String id) {

		return true;
	}
}
