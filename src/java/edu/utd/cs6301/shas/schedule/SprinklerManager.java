/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utd.cs6301.shas.schedule;

import edu.utd.cs6301.shas.entity.Sprinkler;
import edu.utd.cs6301.shas.entity.Sprinklersetting;
import edu.utd.cs6301.shas.sessionbean.SprinklerFacade;
import edu.utd.cs6301.shas.sessionbean.SprinklersettingFacade;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Singleton;

/**
 *
 * @author Zheng
 */
@ManagedBean
@Singleton
public class SprinklerManager {

    @EJB
    private SprinklerFacade sprinklerBean;

    @EJB
    private SprinklersettingFacade sprinklersettingBean;

    private List<Sprinkler> sprinklers;

    private Map<String, List<SprinklerTask>> taskMap;

    public SprinklerManager() {

    }

    @PostConstruct
    public void init() {
        System.out.println(">>>SprinklerManager init()");
        taskMap = new ConcurrentHashMap<>();
        sprinklers = sprinklerBean.findAll();
        SprinklerTask task;
        System.out.println("sprinklers size: " + sprinklers.size());
        for (Sprinkler s : sprinklers) {
            processSettings(s);
        }
    }

    public void updateSetting(Sprinklersetting setting) {
        System.out.println("setting update for: " + setting);
        String sid = setting.getSprinklerid().getSprinklerid();
        List<SprinklerTask> tasks = taskMap.get(sid);
        if(tasks == null)
            return;
        for (SprinklerTask task : tasks) {
            if (task.getSetting().getSettingid() == setting.getSettingid()) {
                task.setSetting(setting);
            }
        }
    }

    public void addSetting(Sprinklersetting setting) {
        System.out.println("new setting added: " + setting);
        String sid = setting.getSprinklerid().getSprinklerid();
        List<SprinklerTask> tasks = taskMap.get(sid);
        if (tasks == null) {
            tasks = new ArrayList<>();
        }

        SprinklerTask task = new SprinklerTask(setting, sprinklerBean, sprinklersettingBean);
        tasks.add(task);
        System.out.println(">>>> adding new task for sprinkler: " + sid
                + "; settingId: " + setting.getSettingid());
        new Thread(task).start();
    }

    public void removeSetting(Sprinklersetting setting) {
        System.out.println("remove setting : " + setting);
        String sid = setting.getSprinklerid().getSprinklerid();
        List<SprinklerTask> tasks = taskMap.get(sid);
        if(tasks == null)
            return;
        for (SprinklerTask task : tasks) {
            if (task.getSetting().getSettingid() == setting.getSettingid()) {
                task.setMarkDeleted(true);
            }
        }
    }

    public void updateSprinkler(Sprinkler s) {
        processSettings(s);
    }
    
    public void removeSprinkler(Sprinkler s){
        processSettings(s, true);
    }

    private void processSettings(Sprinkler s){
        this.processSettings(s, false);
    }
    
    private void processSettings(Sprinkler s, boolean delete) {
        System.out.println("process settings for sprinkler: " + s);
        SprinklerTask task ;
        if(delete){
            List<SprinklerTask> taskList = taskMap.get(s.getSprinklerid());
            taskMap.remove(s.getSprinklerid());
            for(SprinklerTask stask : taskList){
                stask.setMarkDeleted(true);
            }
            return;
        }
        if (s.getControlmode().trim().toLowerCase().contains("auto")) {
            //mode = auto 
            for (Sprinklersetting setting : s.getSprinklersettingCollection()) {
                if (setting.getDayofweek() == Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
                    //setting is today, then put the setting into task and then put into map.
                    task = new SprinklerTask(setting, sprinklerBean, sprinklersettingBean);
                    System.out.println(">>>> adding new task for sprinkler: " + s.getSprinklerid()
                            + "; settingId: " + setting.getSettingid());
                    if (taskMap.get(s.getSprinklerid()) != null) {
                        taskMap.get(s.getSprinklerid()).add(task);
                    } else {
                        List<SprinklerTask> taskList = new ArrayList<>();
                        taskList.add(task);
                        taskMap.put(s.getSprinklerid(), taskList);
                    }
                    new Thread(task).start(); 
                }
            }
        }else{
            //mode = manual
            List<SprinklerTask> taskList = taskMap.get(s.getSprinklerid());
            taskMap.remove(s.getSprinklerid());
            for(SprinklerTask stask : taskList){
                stask.setMarkDeleted(true);
            }
        }
    }
}
