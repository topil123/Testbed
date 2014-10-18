/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utd.cs6301.shas.controller;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import edu.utd.cs6301.shas.entity.Detector;
import edu.utd.cs6301.shas.sessionbean.DetectorFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Zheng
 */
@WebServlet(name = "DetectorServlet", loadOnStartup = 1, urlPatterns = {"/detector"}, asyncSupported=true)
public class DetectorServlet extends HttpServlet {

    @EJB
    private DetectorFacade detectorBean;

    private Gson gson;

    public void init() throws ServletException {
        gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {

            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }

            /**
             * Custom field exclusion goes here
             */
            public boolean shouldSkipField(FieldAttributes f) {
                return false;
            }

        })
                /**
                 * Use serializeNulls method if you want To serialize null
                 * values By default, Gson does not serialize null values
                 */
                .serializeNulls()
                .create();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        
        if (request.getParameter("action") != null) {
            String action = (String) request.getParameter("action");
            String responseStr = null;

            try {
                switch (action) {
                    case "list":
                        responseStr = displayDetector(request);
                        break;
                    case "create":
                        responseStr = createDetector(request);
                        break;
                    case "update":
                        responseStr = updateDetector(request);
                        break;
                    case "delete":
                        responseStr = deleteDetector(request);
                        break;
                }
                response.setContentType("application/json");
                response.getWriter().print(responseStr);
//                System.out.println(">>> reponse has been sent out...");
//                registerAsyncContext(request, response);
//                System.out.println(">>> process finished.");
            } catch (Exception ex) {
                String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getStackTrace() + "}";
                response.getWriter().print(error);
                System.err.println(ex.getMessage());
            }
        }
    }
    
    private void registerAsyncContext(HttpServletRequest request, HttpServletResponse response){
        //register this servlet as a observer to show the event.
        AsyncContext aCtx = request.startAsync(request, response); 
        Set<AsyncContext> eventObservers 
                = (Set<AsyncContext>) request.getServletContext().getAttribute("eventObserver");
        System.out.println(">>>> " + eventObservers.size());
        eventObservers.add(aCtx);
        request.getServletContext().setAttribute("eventObserver", eventObservers);
    }

    private String displayDetector(HttpServletRequest request) throws IOException {
        List<Detector> sprinklers = detectorBean.findAll();

        String listData = gson.toJson(sprinklers);

        //Return Json in the format required by jTable plugin
        return "{\"Result\":\"OK\",\"Records\":" + listData + "}";

    }

    private String createDetector(HttpServletRequest request) throws IOException {
        String id = request.getParameter("detectorid");
        String des = request.getParameter("description");
        String mode = request.getParameter("controlmode");
        String status = request.getParameter("status");
        Detector s = new Detector();
        s.setDetectorid(id);
        s.setDescription(des);
        s.setStatus(status);
        detectorBean.create(s);
        String json = gson.toJson(s);

        return "{\"Result\":\"OK\",\"Record\":" + json + "}";
    }

    private String deleteDetector(HttpServletRequest request) {
        Detector s = detectorBean.find(request.getParameter("detectorid"));
        detectorBean.remove(s);
        return "{\"Result\":\"OK\"}";
    }

    private String updateDetector(HttpServletRequest request) {
        String id = request.getParameter("detectorid");
        String des = request.getParameter("description");
        String status = request.getParameter("status");
        Detector s = new Detector();
        s.setDetectorid(id);
        s.setDescription(des);
        s.setStatus(status);
        detectorBean.edit(s);
        String json = gson.toJson(s);
        // Return Json in the format required by jTable plugin
        return "{\"Result\":\"OK\",\"Record\":" + json + "}";
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
