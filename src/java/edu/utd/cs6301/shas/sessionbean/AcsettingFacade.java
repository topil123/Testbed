/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utd.cs6301.shas.sessionbean;

import edu.utd.cs6301.shas.entity.Acsetting;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Zheng
 */
@Stateless
public class AcsettingFacade extends AbstractFacade<Acsetting> {
    @PersistenceContext(unitName = "TestbedPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AcsettingFacade() {
        super(Acsetting.class);
    }
    
}
