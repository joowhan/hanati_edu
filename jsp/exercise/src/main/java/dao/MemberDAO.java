package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import dto.MemberDTO;

public class MemberDAO {

	private Connection connection;
	private PreparedStatement pstmt;
	private DataSource ds;
	private ResultSet rs;

	public MemberDAO() {
		// TODO Auto-generated constructor stub
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public boolean signUp(MemberDTO memberDTO) {
		String sql = "INSERT INTO TB_USER(NO_USER, ID_USER, NM_USER, NM_PASWD, NO_MOBILE, NM_EMAIL, ST_STATUS, CD_USER_TYPE) "
				+ "VALUES(?,?,?,?,?,?,?,?)";
		String sql2 = "select 'PD' || LPAD(seq_tb_user.nextval, 5, '0') from dual";

		rs = null;
		pstmt = null;
		String noUser = "";

		// noUser 생성
		try {
			connection = ds.getConnection();
			pstmt = connection.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				noUser = rs.getString(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		try {

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, noUser);
			pstmt.setString(2, memberDTO.getIdUser());
			pstmt.setString(3, memberDTO.getNmUser());
			pstmt.setString(4, memberDTO.getNmPaswd());
			pstmt.setString(5, memberDTO.getNoMobile());
			pstmt.setString(6, memberDTO.getNmEmail());
			pstmt.setString(7, "ST01");
			pstmt.setString(8, memberDTO.getCdUserType());

			int rows = pstmt.executeUpdate();
			if (rows > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				assert pstmt != null;
				pstmt.close();
				rs.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isExistId(String id) {
		String sql = "select id_user from tb_user where id_user=?";
		rs = null;
		pstmt = null;
		try {
			connection = ds.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
			return false;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				assert pstmt != null;
				pstmt.close();
				if (rs != null)
					rs.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public String getPassword(String id) {
		String password = "";
		String sql = "select nm_paswd from tb_user where id_user=?";
		rs = null;
		pstmt = null;
		System.out.println("getPassword: " + id);
		try {
			connection = ds.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				password = rs.getString(1);
			}
//            System.out.println(password);
			return password;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				assert pstmt != null;
				pstmt.close();
				rs.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// read member(admin || user) data from database
	public MemberDTO readMember(MemberDTO memberDTO, String id) {

		String sql = "SELECT ID_USER, " + "    NM_USER, NM_PASWD, NO_MOBILE, "
				+ "    NM_EMAIL, ST_STATUS, CD_USER_TYPE " + "	FROM TB_USER " + "	WHERE ID_USER=? ";
		rs = null;
		pstmt = null;
		try {
			System.out.println(id);
			connection = ds.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memberDTO.setIdUser(rs.getString(1));
				memberDTO.setNmUser(rs.getString(2));
				memberDTO.setNmPaswd(rs.getString(3));
				memberDTO.setNoMobile(rs.getString(4));
				memberDTO.setNmEmail(rs.getString(5));
				memberDTO.setStStatus(rs.getString(6));
				memberDTO.setCdUserType(rs.getString(7));
			}

			return memberDTO;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				assert pstmt != null;
				pstmt.close();
				rs.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// update user from database
	public boolean updateUser(MemberDTO memberDTO, String id) {
		String sql = "UPDATE TB_USER SET " + "    NM_USER=?, " + "    NM_PASWD=?, " + "    NO_MOBILE=?, "
				+ "    NM_EMAIL=? " + "    WHERE ID_USER=?";
		pstmt = null;
		try {
			System.out.println(id);
			connection = ds.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, memberDTO.getNmUser());
			pstmt.setString(2, memberDTO.getNmPaswd());
			pstmt.setString(3, memberDTO.getNoMobile());
			pstmt.setString(4, memberDTO.getNmEmail());
			pstmt.setString(5, id);

			int rows = pstmt.executeUpdate();

			return rows > 0;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				assert pstmt != null;
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// withdraw from this system -> user
	public boolean withdrawal(String id) {
		String sql = "update tb_user set st_status = ? where id_user =?";
		pstmt = null;
		try {
			connection = ds.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, "ST02");
			pstmt.setString(2, id);

			int rows = pstmt.executeUpdate();

			return rows > 0;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				assert pstmt != null;
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// read all users
	public ArrayList<MemberDTO> readAllUsers() {
		ArrayList<MemberDTO> users = new ArrayList<MemberDTO>();
		String sql = "SELECT  ID_USER, " + "    NM_USER, NM_PASWD, NO_MOBILE, "
				+ "    NM_EMAIL, ST_STATUS, CD_USER_TYPE " + "FROM TB_USER";
		pstmt = null;
		rs = null;
		try {
			connection = ds.getConnection();
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
//            	if(rs.getString(7).equals("20")) {
//            		continue;
//            	}
				MemberDTO mDto = new MemberDTO();
				mDto.setIdUser(rs.getString(1));
				mDto.setNmUser(rs.getString(2));
				mDto.setNmPaswd(rs.getString(3));
				mDto.setNoMobile(rs.getString(4));
				mDto.setNmEmail(rs.getString(5));
				mDto.setStStatus(rs.getString(6));
				mDto.setCdUserType(rs.getString(7));
				users.add(mDto);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				assert pstmt != null;
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return users;
	}

	// 사용자 상태 변경
	public boolean updateStStatus(MemberDTO memberDTO) {
		String sql = "update tb_user set st_status = ? where id_user =?";
		pstmt = null;
		try {
			connection = ds.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, memberDTO.getStStatus());
			pstmt.setString(2, memberDTO.getIdUser());

			int rows = pstmt.executeUpdate();

			return rows > 0;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				assert pstmt != null;
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	// 사용자 권한 변경
	public boolean updateCdUserType(MemberDTO memberDTO) {
		String sql = "update tb_user set cd_user_type = ? where id_user =?";
		pstmt = null;
		try {
			connection = ds.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, memberDTO.getCdUserType());
			pstmt.setString(2, memberDTO.getIdUser());
			System.out.println(memberDTO.getCdUserType() + " " + memberDTO.getIdUser());
			int rows = pstmt.executeUpdate();

			return rows > 0;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				assert pstmt != null;
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
