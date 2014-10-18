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
import edu.utd.cs6301.shas.entity.Airconditioning;
import edu.utd.cs6301.shas.entity.Acsetting;
import edu.utd.cs6301.shas.sessionbean.AcsettingFacade;
import edu.utd.cs6301.shas.sessionbean.AcsettingFacade;
import edu.utd.cs6301.shas.sessionbean.AirconditioningFacade;
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
@WebServlet(name = "AcSettingServlet", urlPatterns = {"/acSetting"})
public class ACSettingServlet extends HttpServlet {
    @EJB
    private AcsettingFacade acSettingBean;
    
    @EJB
    private AirconditioningFacade acBean;

    private Gson gson;

    public void init() throws ServletException {
        gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {

            public boolean shouldSkipClass(Class<?> clazz) {
                return (clazz == Airconditioning.class);
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
                    responseStr=displayAcSetting(request);
                    break;
                case "create":
                    responseStr=createAcSetting(request);
                    break;
                case "update":
                    responseStr=updateAcSetting(request);
                    break;
                case "delete":
                    responseStr=deleteAcSetting(request);
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
    
     private String displayAcSetting(HttpServletRequest request) throws IOException {
         
         String sprinklerId = request.getParameter("acid");
         Collection<Acsetting> settings = acBean.find(sprinklerId).getAcsettingCollection();
        String listData = gson.toJson(settings);
        //Return Json in the format required by jTable plugin
        return "{\"Result\":\"OK\",\"Records\":" + listData + "}";
       
    }
     
     private String createAcSetting(HttpServletRequest request) throws IOException {
         
         String acid = request.getParameter("acid");
         Airconditioning ac = acBean.find(acid);
         String dayofweek = request.getParameter("dayofweek");
         String startime = request.getParameter("starttime");
         String endtime = request.getParameter("endtime");
         String temp = request.getParameter("temperature");
         Acsetting setting = new Acsetting();
         setting.setDayofweek(new Integer(dayofweek));
         setting.setStarttime(startime);
         setting.setEndtime(endtime);
         setting.setTemperature(new Integer(temp));
         setting.setAcid(ac);
         acSettingBean.create(setting);
        String json = gson.toJson(setting);
        
        return "{\"Result\":\"OK\",\"Record\":" + json + "}";
    }

    private String deleteAcSetting(HttpServletRequest request) {
        String settingid = request.getParameter("settingid");
        Acsetting setting = acSettingBean.find(new Integer(settingid));
        acSettingBean.remove(setting);
        return "{\"Result\":\"OK\"}";
    }

    private String updateAcSetting(HttpServletRequest request) {
        String settingid = request.getParameter("settingid");
        String acid = request.getParameter("acid");
        Airconditioning ac = acBean.find(acid);
        Acsetting setting = new Acsetting();
        setting.setSettingid(new Integer(settingid));
        setting.setDayofweek(new Integer(request.getParameter("dayofweek")));
        setting.setStarttime(request.getParameter("starttime"));
        setting.setEndtime(request.getParameter("endtime"));
        setting.setTemperature(new Integer(request.getParameter("temperature")));
        setting.setAcid(ac);
        acSettingBean.edit(setting);
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
