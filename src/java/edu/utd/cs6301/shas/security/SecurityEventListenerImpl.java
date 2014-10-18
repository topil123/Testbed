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
public class SecurityEventListenerImpl implements EventListenerInterface{
    @Override
    public void process(Event e){
        System.out.println("Got event: " + e);
    }
    
    @Override
    public void cancel(CancelableEvent ce){
    
    }
}
