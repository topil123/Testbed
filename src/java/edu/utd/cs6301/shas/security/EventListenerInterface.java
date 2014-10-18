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
public interface EventListenerInterface {
    public void process(Event e);
    public void cancel(CancelableEvent ce);
}
