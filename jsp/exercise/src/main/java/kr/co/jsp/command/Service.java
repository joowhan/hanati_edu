package kr.co.jsp.command;

import java.util.ArrayList;

import dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Service {
	public ArrayList<MemberDTO> execute(HttpServletRequest request, HttpServletResponse response);
}
