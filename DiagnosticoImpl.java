/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Ejb;

import Entidades.Informe;
import Entidades.Persona;
import Entidades.Tratamiento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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

    @Override
    public List<Informe> traerInformes(Integer nss) {
            List<Informe> informes;
           try {
            informes = em.createNamedQuery("Informe.traer", Informe.class)
                    .setParameter("nss", nss)
                    .getResultList();                
            } catch (NoResultException ex) {
            informes = null;
        }  
        return informes;
    }
   
    
}
