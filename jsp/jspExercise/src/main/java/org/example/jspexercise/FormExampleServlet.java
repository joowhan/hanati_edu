package org.example.jspexercise;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "FormExampleServlet", value = "/formEx")

public class FormExampleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String id = req.getParameter("id");
        String paswd = req.getParameter("paswd");

        resp.setContentType("text/html; charset-UTF-8");

        PrintWriter pw = resp.getWriter();

        pw.println("<html>");
        pw.println("<head>");
        pw.println("</head>");
        pw.println("<body>" );
        pw.println("</body>");
        pw.println("<h1> hi ~ </h1>");
        pw.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    }
}