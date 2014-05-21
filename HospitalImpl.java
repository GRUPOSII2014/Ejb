/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Ejb;

import Entidades.Habitacion;
import Entidades.Hospital;
import Entidades.Planta;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Angel
 */
@Stateless
public class HospitalImpl implements HospitalEjb {
    
     @PersistenceContext(unitName = "HospitalEE-ejbPU")
    private EntityManager em;

    @Override
    public void crearHospital(Hospital h) {
        em.persist(h);
        }
    
    @Override
    public List<Hospital> getHospitales(){
        TypedQuery<Hospital> query = em.createNamedQuery("hospitales", Hospital.class);
        return query.getResultList();
    }
    
    @Override
    public List<Planta> getPlantas(){
        TypedQuery<Planta> query = em.createNamedQuery("Planta.all",Planta.class);
        return query.getResultList();
    }
    
    @Override
    public void crearPlanta(Planta p){
        em.persist(p);
    }
    
    @Override
    public void crearHabitacion(Habitacion h){
        em.persist(h.getPlanta().getHabitaciones().add(h));
        em.persist(h);
    }

    
}
