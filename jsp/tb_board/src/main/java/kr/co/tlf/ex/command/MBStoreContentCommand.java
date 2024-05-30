package kr.co.tlf.ex.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.tlf.ex.dao.MBDao;

public class MBStoreContentCommand implements MBCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		MBDao mbDao = new MBDao();

		mbDao.InsertPost(request.getParameter("nmWriter"), request.getParameter("nmTitle"),
				request.getParameter("nmContent"));
	}

}
