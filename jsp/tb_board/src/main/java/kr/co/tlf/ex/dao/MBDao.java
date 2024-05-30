package kr.co.tlf.ex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.co.tlf.ex.dto.MBDto;

public class MBDao {
	private Connection connection;
	private PreparedStatement pstmt;
	private DataSource ds;
	private ResultSet rs;

	public MBDao() {
		// TODO Auto-generated constructor stub
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<MBDto> readBoardList() {
		ArrayList<MBDto> boardList = new ArrayList<MBDto>();

		String sql = "select nb_board, nm_title, nm_content, nm_writer, da_writer, cn_hit, nb_group, nb_step, nb_indent "
				+ " from tb_board " + "order by nb_board desc";
		pstmt = null;
		rs = null;
		try {
			connection = ds.getConnection();
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				MBDto mbDto = new MBDto();

				mbDto.setNbBoard(rs.getString(1));
				mbDto.setNmTitle(rs.getString(2));
				mbDto.setNmContent(rs.getString(3));
				mbDto.setNmWriter(rs.getString(4));
				mbDto.setDaWriter(rs.getString(5));
				mbDto.setCnHit(rs.getInt(6));
				mbDto.setNbGroup(rs.getInt(7));
				mbDto.setNbStep(rs.getInt(8));
				mbDto.setNbIndent(rs.getInt(9));
				boardList.add(mbDto);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("read board list");
		return boardList;
	}

	// 메인 글 작성
	public void InsertPost(String nmWriter, String nmTitle, String nmContent) {
		String sql = "insert into tb_board(nb_board, nm_title, nm_content, nm_writer, da_writer, cn_hit, nb_group, nb_step, nb_indent) "
				+ " values(seq_tb_board.nextval,?,?,?,SYSDATE,?,seq_tb_board.nextval,?,?)";
		// request

		try {
			connection = ds.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, nmTitle);
			pstmt.setString(2, nmContent);
			pstmt.setString(3, nmWriter);
			pstmt.setInt(4, 0);
			pstmt.setInt(5, 0);
			pstmt.setInt(6, 0);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public MBDto readPost(int nbBoard) {

		String sql = "select nb_board, nm_title, nm_content, nm_writer, da_writer, cn_hit, nb_group, nb_step, nb_indent "
				+ " from tb_board " + "where nb_board=? " + "order by nb_board desc";
		pstmt = null;
		rs = null;
		System.out.println("enter readPost");
		try {
			connection = ds.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, nbBoard);
			rs = pstmt.executeQuery();
			MBDto mbDto = new MBDto();
			while (rs.next()) {
				System.out.println("nbBoard data 존재");

				mbDto.setNbBoard(rs.getString(1));
				mbDto.setNmTitle(rs.getString(2));
				mbDto.setNmContent(rs.getString(3));
				mbDto.setNmWriter(rs.getString(4));
				mbDto.setDaWriter(rs.getString(5));
				mbDto.setCnHit(rs.getInt(6));
				mbDto.setNbGroup(rs.getInt(7));
				mbDto.setNbStep(rs.getInt(8));
				mbDto.setNbIndent(rs.getInt(9));

			}
			return mbDto;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public void reply(String nbMvcBoard, String nmWriter, String nmTitle, String nmContent, String nbGroup,
			String nbStep, String nbIndent) {

		replyShape(nbGroup, nbStep);

		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "insert into tb_board(nb_board, nm_writer, nm_title, nm_content, nb_group, nb_step, nb_indent) values(seq_tb_board.nextval, ?, ?, ?, ?, ?, ?)";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, nmWriter);
			pstmt.setString(2, nmTitle);
			pstmt.setString(3, nmContent);
			pstmt.setInt(4, Integer.parseInt(nbGroup));
			pstmt.setInt(5, Integer.parseInt(nbStep) + 1);
			pstmt.setInt(6, Integer.parseInt(nbIndent) + 1);

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void replyShape(String nbGroup, String nbStep) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "update tb_board set nb_step = nb_step + 1 where nb_group = ? and nb_step > ?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, Integer.parseInt(nbGroup));
			pstmt.setInt(2, Integer.parseInt(nbStep));

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
