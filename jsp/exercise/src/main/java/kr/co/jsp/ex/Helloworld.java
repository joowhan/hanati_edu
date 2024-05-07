package kr.co.jsp.ex;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class Helloworld
 */

@WebServlet(urlPatterns = {"/loginEx"}, initParams = {@WebInitParam(name = "dbId", value = "asdf")})
public class Helloworld extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Helloworld() {
        // TODO Auto-generated constructor stub
    }
    @PostConstruct
    public void postConstruct() {
    	System.out.println("postConstruct");
    }
    
	public void init() {
		System.out.println("init");
	}
	
    @PreDestroy
    public void preDestroy() {
    	System.out.println("predestroy 종료");
    }
    
	public void destroy() {
		System.out.println("destroy");
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String dbId = getInitParameter("dbId");
		String dbId2 = getServletContext().getInitParameter("dbId");
		System.out.println(dbId + " " + dbId2);
        response.setContentType("text/html; charset-UTF-8");

        PrintWriter pw = response.getWriter();
        
        pw.println("<html>");
        pw.println("<head>");
        pw.println("</head>");
        pw.println("<body>" );
        pw.println("</body>");
        pw.println("<h1> hi ~ </h1>");
        pw.println("<h3>"+dbId+"</h3>");
        pw.println("</html>");
	}

}
