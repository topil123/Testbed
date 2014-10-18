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
import edu.utd.cs6301.shas.entity.Airconditioning;
import edu.utd.cs6301.shas.entity.Acsetting;
import edu.utd.cs6301.shas.sessionbean.AirconditioningFacade;
import edu.utd.cs6301.shas.sessionbean.AirconditioningFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Zheng
 */
@WebServlet(name = "AirConditioningServlet", loadOnStartup = 1, urlPatterns = {"/ac"})
public class AirConditioningServlet extends HttpServlet {

    @EJB
    private AirconditioningFacade acBean;

    private Gson gson;

    public void init() throws ServletException {
        gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {

            public boolean shouldSkipClass(Class<?> clazz) {
                return (clazz == Acsetting.class);
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException
             {

        if (request.getParameter("action") != null) {
            String action = (String) request.getParameter("action");
            String responseStr = null;
            
            try{
            switch (action) {
                case "list":
                    responseStr=displayAirConditioning();
                    break;
                case "create":
                    responseStr=createAirConditioning(request);
                    break;
                case "update":
                    responseStr=updateAirConditioning(request);
                    break;
                case "delete":
                    responseStr=deleteAirConditioning(request);
                    break;
            }
            response.setContentType("application/json");
            response.getWriter().print(responseStr);
            }catch (Exception ex) {
                           String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace()+"}";
                           response.getWriter().print(error);
                           System.err.println(ex.getMessage());
            }
        }
    }

    private String displayAirConditioning() throws IOException {
        List<Airconditioning> acs = acBean.findAll();

        String listData = gson.toJson(acs);

        //Return Json in the format required by jTable plugin
        return "{\"Result\":\"OK\",\"Records\":" + listData + "}";
       
    }

    private String createAirConditioning(HttpServletRequest request) throws IOException {
        String id = request.getParameter("acid");
        String des = request.getParameter("description");
        String mode = request.getParameter("controlmode");
        String status = request.getParameter("status");
        int defaultTemp = new Integer(request.getParameter("defaulttemperature"));
        Airconditioning s = new Airconditioning();
        s.setAcid(id);
        s.setDescription(des);
        s.setControlmode(mode);
        s.setStatus(status);
        s.setDefaulttemperature(defaultTemp);
        acBean.create(s);
        String json = gson.toJson(s);
        
        return "{\"Result\":\"OK\",\"Record\":" + json + "}";
    }

    private String deleteAirConditioning(HttpServletRequest request) {
        Airconditioning s = acBean.find(request.getParameter("acid"));
        acBean.remove(s);
        return "{\"Result\":\"OK\"}";
    }

    private String updateAirConditioning(HttpServletRequest request) {
        String id = request.getParameter("acid");
        String des = request.getParameter("description");
        String mode = request.getParameter("controlmode");
        String status = request.getParameter("status");
        int defaultTemp = new Integer(request.getParameter("defaulttemperature"));
        Airconditioning s = new Airconditioning();
        s.setAcid(id);
        s.setDescription(des);
        s.setControlmode(mode);
        s.setStatus(status);
        s.setDefaulttemperature(defaultTemp);
        acBean.edit(s);
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
