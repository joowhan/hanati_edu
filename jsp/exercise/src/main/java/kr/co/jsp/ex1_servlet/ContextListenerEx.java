package kr.co.jsp.ex1_servlet;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class ContextListenterEx
 *
 */
@WebListener
public class ContextListenerEx implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ContextListenerEx() {
        // TODO Auto-generated constructor stub
    }
    
	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	System.out.println("contextInitialized ");
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */

	
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	System.out.println("contextDestroyed 종료");
    }
	
}
