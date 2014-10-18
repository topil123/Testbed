/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utd.cs6301.shas.security;

import java.util.ArrayList;
import java.util.List;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Zheng
 */

public class EventCenter {
    private final static EventCenter instance = new EventCenter();
    private final static List<EventListenerInterface> listeners = new ArrayList<>();
    
    private EventCenter(){
    
    }
    
    public static EventCenter getInstance(){
        return instance;
    }
    
    
    public void addListener(EventListenerInterface l){
        listeners.add(l);
    }
    
    public List<EventListenerInterface> getListeners(){
        return listeners;
    }
    
    public void fireEvent(Event e){
        for(EventListenerInterface l : listeners){
            l.process(e);
        }
    }
    
    public void cancelEvent(CancelableEvent e){
        for(EventListenerInterface l : listeners){
            l.process(e);
        }
    }
}
