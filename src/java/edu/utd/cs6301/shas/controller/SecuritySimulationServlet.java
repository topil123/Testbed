/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utd.cs6301.shas.controller;

import edu.utd.cs6301.shas.entity.Detector;
import edu.utd.cs6301.shas.security.Event;
import edu.utd.cs6301.shas.security.Event.EventType;
import edu.utd.cs6301.shas.sessionbean.DetectorFacade;
import edu.utd.cs6301.shas.sessionbean.SecurityEventBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.AsyncContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Zheng
 */
@WebServlet(asyncSupported=true, name = "SecuritySimulationServlet", urlPatterns = {"/SecuritySimulator"})
public class SecuritySimulationServlet extends HttpServlet {

    @Inject
    SecurityEventBean eventBean;
    
    @EJB
    private DetectorFacade detectorBean;

    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          List<Detector> detectors = detectorBean.findAll();
        request.setAttribute("detectors", detectors);
        request.getRequestDispatcher("simulator_security.jsp").forward(request, response);
              
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String detectorId = request.getParameter("detectorid");
        System.out.println(">>>>>" + detectorId);
        EventType type = null;
        if(detectorId.toLowerCase().contains("fire"))
            type = EventType.FIRE;
        else if(detectorId.toLowerCase().contains("co"))
            type = EventType.CO;
        else if(detectorId.toLowerCase().contains("smoke"))
            type = EventType.SMOKE;
        else if(detectorId.toLowerCase().contains("gas"))
            type = EventType.GAS;
        else if(detectorId.toLowerCase().contains("door"))
            type = EventType.DOOR;
        else if(detectorId.toLowerCase().contains("window"))
            type = EventType.WINDOW;
        else
            type = EventType.FIRE;
            
        
        Event e = new Event(type, detectorId, String.valueOf(Calendar.getInstance().getTime()));
        eventBean.addEvent(e);
        response.getWriter().write("Event sent.");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
    
}
