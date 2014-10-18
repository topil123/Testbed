/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utd.cs6301.shas.security;

/**
 *
 * @author Zheng
 */
public class Event {

 
    public static enum EventType {FIRE, CO, GAS, SMOKE, WINDOW, DOOR };
    EventType type;
    String detectorId;
    String time;
    
    public Event(EventType type, String detector, String time){
        this.type = type;
        this.detectorId = detector;
        this.time = time;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Type:  ")
                .append(type)
                .append("\t Detector:  ")
                .append(detectorId)
                .append("\t Time: ")
                .append(time);
        return sb.toString();
    }
    
}
