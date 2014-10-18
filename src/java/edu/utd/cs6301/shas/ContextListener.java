/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utd.cs6301.shas;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author Zheng
 */
@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        System.out.println(">>>>>> we are ready...");
//        final Set<AsyncContext> eventO = new HashSet<>();
//        sce.getServletContext().setAttribute("eventObserver", eventO);
//        Queue<Event> eventQ = new ConcurrentLinkedQueue<>();
//        sce.getServletContext().setAttribute("eventQueue", eventQ);

//        new Thread(new EventManager(sce.getServletContext())).start(); //START DAEMON
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
