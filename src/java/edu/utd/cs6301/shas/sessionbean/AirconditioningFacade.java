/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utd.cs6301.shas.sessionbean;

import edu.utd.cs6301.shas.entity.Airconditioning;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Zheng
 */
@Stateless
public class AirconditioningFacade extends AbstractFacade<Airconditioning> {
    @PersistenceContext(unitName = "TestbedPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AirconditioningFacade() {
        super(Airconditioning.class);
    }
    
}
