package kr.co.jsp.command;

import java.util.ArrayList;

import dao.MemberDAO;
import dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberAllService implements Service {

	@Override
	public ArrayList<MemberDTO> execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		MemberDAO mDao = new MemberDAO();
		return mDao.readAllUsers();
	}

}
