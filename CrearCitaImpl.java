/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Ejb;

import Entidades.Cita;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Angel
 */
@Stateless
public class CrearCitaImpl implements CrearCitaEjb{
    @PersistenceContext(unitName = "HospitalEE-ejbPU")
    EntityManager em;
   
    @Override
    public void creaCita(Cita c) {
        em.persist(c);
    }
    
    
}
