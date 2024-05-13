package kr.co.jsp.control;

import java.io.IOException;
import java.util.ArrayList;

import dto.MemberDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.jsp.command.MemberAllService;
import kr.co.jsp.command.Service;

/**
 * Servlet implementation class FrontCon
 */
@WebServlet("*.do")
public class FrontCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontCon() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doGet");
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doPost");
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring(contextPath.length());

		// read all member
		if (command.equals("/memberAll.do")) {
			response.setContentType("text/html; charset=UTF-8");
			Service service = new MemberAllService();
			ArrayList<MemberDTO> allDTO = service.execute(request, response);

			for (int i = 0; i < allDTO.size(); i++) {
				MemberDTO mDto = allDTO.get(i);

				String idUser = mDto.getIdUser();
			}
		}
	}

//FrontController test
	private void actionDo1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("actionDo");

		String uri = request.getRequestURI();
		System.out.println("uri: " + uri);

		String conPath = request.getContextPath();
		System.out.println("conPath: " + conPath);

		String command = uri.substring(conPath.length());
		System.out.println("command: " + command);

		if (command.equals("insert.do")) {
			System.out.println("/insert.do");
			System.out.println("--------------------------");
		} else if (command.equals("update.do")) {
			System.out.println("/update.do");
			System.out.println("--------------------------");
		} else if (command.equals("select.do")) {
			System.out.println("/select.do");
			System.out.println("--------------------------");
		} else if (command.equals("delete.do")) {
			System.out.println("/delete.do");
			System.out.println("--------------------------");
		}
	}
}