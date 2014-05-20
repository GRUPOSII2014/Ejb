/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Ejb;

import Entidades.Informe;
import Entidades.Tratamiento;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ricardo
 */

@Stateless
public class DiagnosticoImpl implements DiagnosticoEjb{
    @PersistenceContext(unitName = "HospitalEE-ejbPU")
    private EntityManager em;
   
    @Override
    public void anadeSegOp(Informe i) {
        em.persist(i);
    }

    @Override
    public void anadeInf(Informe i) {
         em.persist(i);
    }

    @Override
    public void actualizaInf(Informe i) {
        em.merge(i);
    }

    @Override
    public void creaTratamiento(Tratamiento t) {
       em.persist(t);
    }

    @Override
    public void actuTratamiento(Tratamiento t) {
        em.merge(t);
    }
    
}
