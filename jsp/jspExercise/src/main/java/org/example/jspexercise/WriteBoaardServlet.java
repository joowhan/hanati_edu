package org.example.jspexercise;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "WriteBoaardServlet", value = "/write-board")

public class WriteBoaardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String writer = req.getParameter("writer");

        resp.setContentType("text/html; charset-UTF-8");

        PrintWriter pw = resp.getWriter();

        pw.println("<html>");
        pw.println("<head>");
        pw.println("</head>");
        pw.println("<body>" );
        pw.println("</body>");
        pw.println("<h1> "+title+"</h1>");
        pw.println("<a>"+content+"</a>");
        pw.println("<a>"+writer+"</a>");
        pw.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    }
}