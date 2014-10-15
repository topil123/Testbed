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
import edu.utd.cs6301.shas.entity.Sprinkler;
import edu.utd.cs6301.shas.entity.Sprinklersetting;
import edu.utd.cs6301.shas.sessionbean.SprinklerFacade;
import edu.utd.cs6301.shas.sessionbean.SprinklersettingFacade;
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
@WebServlet(name = "SprinklerSettingServlet", urlPatterns = {"/sprinklerSetting"})
public class SprinklerSettingServlet extends HttpServlet {
    @EJB
    private SprinklersettingFacade sprinklerSettingBean;
    
    @EJB
    private SprinklerFacade sprinklerBean;

    private Gson gson;

    public void init() throws ServletException {
        gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {

            public boolean shouldSkipClass(Class<?> clazz) {
                return (clazz == Sprinkler.class);
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("action") != null) {
            String action = (String) request.getParameter("action");
            String responseStr = null;
            
            try{
            switch (action) {
                case "list":
                    responseStr=displaySprinklerSetting(request, response);
                    break;
                case "create":
                    responseStr=createSprinklerSetting(request, response);
                    break;
                case "update":
                    responseStr=updateSprinklerSetting(request, response);
                    break;
                case "delete":
                    responseStr=deleteSprinklerSetting(request, response);
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
    
     private String displaySprinklerSetting(HttpServletRequest request, HttpServletResponse response) throws IOException {
         
         String sprinklerId = request.getParameter("sprinklerid");
         System.out.println(">>>>>>> " +sprinklerId);
         Collection<Sprinklersetting> settings = sprinklerBean.find(sprinklerId).getSprinklersettingCollection();
         System.out.println(">>>>>" + settings);
        String listData = gson.toJson(settings);
         System.out.println(">>>>listdata " + listData);
        //Return Json in the format required by jTable plugin
        return "{\"Result\":\"OK\",\"Records\":" + listData + "}";
       
    }
     
     private String createSprinklerSetting(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return null;
    }

    private String deleteSprinklerSetting(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    private String updateSprinklerSetting(HttpServletRequest request, HttpServletResponse response) {
        return null;
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
