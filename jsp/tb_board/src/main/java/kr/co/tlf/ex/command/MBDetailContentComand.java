package kr.co.tlf.ex.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.tlf.ex.dao.MBDao;
import kr.co.tlf.ex.dto.MBDto;

public class MBDetailContentComand implements MBCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int nbBoard = Integer.parseInt(request.getParameter("num"));
		System.out.println("DetailContent" + nbBoard);
		MBDao mbDao = new MBDao();
		MBDto mbDto = mbDao.readPost(nbBoard);

		request.setAttribute("content_view", mbDto);
	}

}
