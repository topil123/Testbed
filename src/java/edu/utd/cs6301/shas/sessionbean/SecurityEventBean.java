/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utd.cs6301.shas.sessionbean;

import edu.utd.cs6301.shas.security.Event;
import edu.utd.cs6301.shas.security.Event.EventType;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author Zheng
 */
@Singleton
@Startup
public class SecurityEventBean {
    Queue<Event> eventQueue;
    
    public SecurityEventBean(){
        init();
    }
    
    private void init(){
        eventQueue = new ConcurrentLinkedQueue<>();
//        eventQueue.add(new Event(EventType.FIRE, "fire_01", String.valueOf(System.currentTimeMillis())));
//        eventQueue.add(new Event(EventType.FIRE, "fire_02", String.valueOf(System.currentTimeMillis())));
//        eventQueue.add(new Event(EventType.CO, "co_01", String.valueOf(System.currentTimeMillis())));
    }
    
    public Queue<Event> getEventQueue(){
        return eventQueue;
    }
    
    public void addEvent(Event e){
        eventQueue.add(e);
    }
}
