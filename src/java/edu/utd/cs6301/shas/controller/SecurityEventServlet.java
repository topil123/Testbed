/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utd.cs6301.shas.controller;

import edu.utd.cs6301.shas.security.Event;
import edu.utd.cs6301.shas.security.Event.EventType;
import edu.utd.cs6301.shas.sessionbean.SecurityEventBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Queue;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Zheng
 */
@WebServlet(name = "SecurityEventServlet", urlPatterns = {"/SecurityEvent"})
public class SecurityEventServlet extends HttpServlet {

    @Inject
    SecurityEventBean eventBean;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println(">>>> got request from client....");
        //content type must be set to text/event-stream
        response.setContentType("text/event-stream");

        //encoding must be set to UTF-8
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        Queue<Event> eventQ;
        while (true) {
            eventQ = eventBean.getEventQueue();
            if (!eventQ.isEmpty()) // There are unpublished new events.
            {

                Event event = eventQ.poll();
                writer.write((event.getType() == EventType.DOOR
                        || event.getType() == EventType.WINDOW) ? "event:Security\n" : "event:Fire\n");
                //follow the format, otherwise the response could not be recognized by the client.
                writer.write("data: " + event + "\n\n");
                response.flushBuffer();
                System.out.println(">>>> push event: " + event);
            }
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
        processRequest(request, response);
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
