/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Ejb;

import Entidades.Persona;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Angel
 */

public class CrearCitaImpl implements CrearCitaEjb{
    @PersistenceContext(unitName = "HospitalEE-ejbPU")
    EntityManager em;
    
    @Override
    public Persona buscarPersona(Integer nss) {
        return em.find(Persona.class, nss);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void crearCita() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
