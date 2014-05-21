/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Ejb;

import Entidades.Cama;
import Entidades.Habitacion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Fernando
 */
@Stateless
public class crearCamaImpl implements crearCamaEjb {
    
    @PersistenceContext(unitName = "HospitalEE-ejbPU")
     private EntityManager em;
    

    @Override
    public void crearCama(Cama c) {
        em.persist(c);
    }

    @Override
    public List<Habitacion> todasHabitaciones() {
        TypedQuery<Habitacion> query = em.createNamedQuery("Habitacion.all", Habitacion.class);
        return query.getResultList();
       
    }

    @Override
    public List<Cama> todasCamas() {
         TypedQuery<Cama> query = em.createNamedQuery("Cama.all", Cama.class);
        return query.getResultList();
    }

    @Override
    public String comprobarCama(Cama c) {
        if (c.getHabitacion().getCamas().size()<2)
             crearCama(c);
        else
            return "Demasiadas camas en la misma habitacion";
        return "Cama creada correctamente";
    }

  
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */