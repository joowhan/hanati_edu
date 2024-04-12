package org.example.jspexercise;

import java.io.*;
import java.text.SimpleDateFormat;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    private String messsage;

    @PostConstruct
    public void start(){
        System.out.println("PostConstruct");
    }
    public void init() {
        messsage ="hello World";
    }

    public void destroy(){
        System.out.println("아" );
    }
    @PreDestroy
    public void wow(){
        System.out.println("W");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("doPost");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter pw = response.getWriter();

        pw.println("<html>");
        pw.println("<head>");
        pw.println("</head>");

        pw.println("<body>");
        pw.println("<h1> GET 방식 </h1>");
        pw.println("</body>");
        pw.println("</html>");

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("doPost");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter pw = response.getWriter();

        pw.println("<html>");
        pw.println("<head>");
        pw.println("</head>");

        pw.println("<body>");
        pw.println("<h1> GET 방식 </h1>");
        pw.println("</body>");
        pw.println("</html>");

    }

}