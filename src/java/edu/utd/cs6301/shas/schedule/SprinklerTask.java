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
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zheng
 */
public class SprinklerTask implements Runnable {
    private boolean markDeleted;
    private Sprinklersetting setting;
    private SprinklerFacade sBean;
    private SprinklersettingFacade ssBean;

    private int status = 0;  // 0: waiting to run   1: running   2: finished   3: canceled

    public SprinklerTask(Sprinklersetting setting, SprinklerFacade sBean, SprinklersettingFacade ssBean) {
        this.setting = setting;
        this.sBean = sBean;
        this.ssBean = ssBean;
    }

    public Sprinklersetting getSetting() {
        return setting;
    }

    public void setSetting(Sprinklersetting setting) {
        this.setting = setting;
    }

    public boolean isMarkDeleted() {
        return markDeleted;
    }

    public void setMarkDeleted(boolean markDeleted) {
        this.markDeleted = markDeleted;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void run() {

        Sprinkler s;
        while (true) {
            String[] tmp = setting.getStarttime().trim().split(":");
            int startHour = new Integer(tmp[0]);
            int startMin = new Integer(tmp[1]);
            tmp = setting.getEndtime().trim().split(":");
            int endHour = new Integer(tmp[0]);
            int endMin = new Integer(tmp[1]);
            int currHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            int currMin = Calendar.getInstance().get(Calendar.MINUTE);

            if(markDeleted){
                if(status ==0){
                    System.out.println(">> cancel not started.");
                    status =3;
                    break;
                }
                else{
                    System.out.println(">> cancel running.");
                    s = sBean.find(setting.getSprinklerid().getSprinklerid());
                    s.setStatus("off");
                    sBean.edit(s);
                    status = 3;
                    break;
                }
                    
            }
            if ((endHour < currHour)
                    || (endHour == currHour && endMin < currMin)) {
                System.out.println(">> finish branch.");
                s = sBean.find(setting.getSprinklerid().getSprinklerid());
                s.setStatus("off");
                sBean.edit(s);
                status = 2;
//                break;
            } else if ((currHour < startHour)
                    || (currHour == startHour && currMin < startMin)) {
                System.out.println(">> non start branch.");
                s = sBean.find(setting.getSprinklerid().getSprinklerid());
                s.setStatus("off");
                sBean.edit(s);
                status = 0;

            } else {
                System.out.println(">> running branch.");
                s = sBean.find(setting.getSprinklerid().getSprinklerid());
                s.setStatus("on");
                sBean.edit(s);
                status = 1;
            }

            System.out.println("settingId: " + setting.getSettingid()
                    + " ; currTime: " + currHour + ":" + currMin
                    + " ; startTime: " + startHour + ":" + startMin
                    + " ; endTime: " + endHour + ":" + endMin
                    + " ; status: " + status);
            try {
                Thread.sleep(60 * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(SprinklerTask.class.getName()).log(Level.SEVERE, null, ex);
            }

        }  

    }
}
