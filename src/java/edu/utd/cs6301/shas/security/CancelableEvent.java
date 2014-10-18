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
public class CancelableEvent extends Event{
    private boolean isCanceled;

    public CancelableEvent(EventType type, String detector, String time) {
        super(type, detector, time);
    }
    
    public void cancel(){
        this.isCanceled = true;
    }
    
    public boolean isCanceled(){
        return isCanceled;
    }
}
