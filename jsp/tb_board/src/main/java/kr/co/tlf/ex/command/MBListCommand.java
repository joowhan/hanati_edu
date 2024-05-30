package kr.co.tlf.ex.command;

import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.tlf.ex.dao.MBDao;
import kr.co.tlf.ex.dto.MBDto;

// write content
public class MBListCommand implements MBCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

//		String nbBoard = request.getParameter("nbBoard");
		MBDao mbDao = new MBDao();
		ArrayList<MBDto> mbDtoList = mbDao.readBoardList();

		request.setAttribute("list", mbDtoList);

	}
}
