package kr.co.tlf.ex.frontcontroller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.tlf.ex.command.MBCommand;
import kr.co.tlf.ex.command.MBDetailContentComand;
import kr.co.tlf.ex.command.MBListCommand;
import kr.co.tlf.ex.command.MBStoreContentCommand;

/**
 * Servlet implementation class MBFrontController
 */
@WebServlet("*.do")
public class MBFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MBFrontController() {
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
		System.out.println("actionDo");
		request.setCharacterEncoding("UTF-8");
		String viewPage = null;
		MBCommand cmd = null;

		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		System.out.println(command);
		// 글쓰기 화면 출력
		if (command.equals("/content_write.do")) {
			// 화면 출력
			viewPage = "content_write.jsp";
		}

		// 글 저장 후 메인 이동
		else if (command.equals("/content_stored.do")) {
			System.out.println("/content_stored.do");
			cmd = new MBStoreContentCommand();
			cmd.execute(request, response);
			viewPage = "/list.do";
		}
		// 글 목록 화면 출력
		else if (command.equals("/list.do")) {
			System.out.println("list.do Controller1");
			cmd = new MBListCommand();
			cmd.execute(request, response);
			viewPage = "list.jsp";

		} else if (command.equals("/readDetail.do")) {
			cmd = new MBDetailContentComand();
			cmd.execute(request, response);
			viewPage = "content_view.jsp";
		}
		if (command.equals("/content_stored.do")) {
			response.sendRedirect("list.do");
		} else {
			RequestDispatcher reqDpt = request.getRequestDispatcher(viewPage);
			reqDpt.forward(request, response);
		}

	}

}
