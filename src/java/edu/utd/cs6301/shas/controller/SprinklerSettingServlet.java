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
import java.math.BigInteger;
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
                    responseStr=displaySprinklerSetting(request);
                    break;
                case "create":
                    responseStr=createSprinklerSetting(request);
                    break;
                case "update":
                    responseStr=updateSprinklerSetting(request);
                    break;
                case "delete":
                    responseStr=deleteSprinklerSetting(request);
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
    
     private String displaySprinklerSetting(HttpServletRequest request) throws IOException {
         
         String sprinklerId = request.getParameter("sprinklerid");
         Collection<Sprinklersetting> settings = sprinklerBean.find(sprinklerId).getSprinklersettingCollection();
        String listData = gson.toJson(settings);
        //Return Json in the format required by jTable plugin
        return "{\"Result\":\"OK\",\"Records\":" + listData + "}";
       
    }
     
     private String createSprinklerSetting(HttpServletRequest request) throws IOException {
         
         String sprinklerid = request.getParameter("sprinklerid");
         Sprinkler sprinkler = sprinklerBean.find(sprinklerid);
         String dayofweek = request.getParameter("dayofweek");
         String startime = request.getParameter("starttime");
         String endtime = request.getParameter("endtime");
         Sprinklersetting setting = new Sprinklersetting();
         setting.setDayofweek(new Integer(dayofweek));
         setting.setStarttime(startime);
         setting.setEndtime(endtime);
         setting.setSprinklerid(sprinkler);
         sprinklerSettingBean.create(setting);
        String json = gson.toJson(setting);
        
        return "{\"Result\":\"OK\",\"Record\":" + json + "}";
    }

    private String deleteSprinklerSetting(HttpServletRequest request) {
        String settingid = request.getParameter("settingid");
        Sprinklersetting setting = sprinklerSettingBean.find(new Integer(settingid));
        sprinklerSettingBean.remove(setting);
        return "{\"Result\":\"OK\"}";
    }

    private String updateSprinklerSetting(HttpServletRequest request) {
        String settingid = request.getParameter("settingid");
        String sprinklerid = request.getParameter("sprinklerid");
         Sprinkler sprinkler = sprinklerBean.find(sprinklerid);
        Sprinklersetting setting = new Sprinklersetting();
        setting.setSettingid(new Integer(settingid));
        setting.setDayofweek(new Integer(request.getParameter("dayofweek")));
        setting.setStarttime(request.getParameter("starttime"));
        setting.setEndtime(request.getParameter("endtime"));
        setting.setSprinklerid(sprinkler);
        sprinklerSettingBean.edit(setting);
        String json = gson.toJson(setting);
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
