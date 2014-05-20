/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Ejb;

import Entidades.Alerta;
import Entidades.Persona;
import Entidades.Trabajador;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ricardo
 */
@Stateless
public class EventosImpl implements EventosEjb {

    @PersistenceContext(unitName = "HospitalEE-ejbPU")
    private EntityManager em;
    
    @EJB
    private PersonaEjb ejb;
    
    
    @Override
    public void crearEventos(Alerta a) {
        em.persist(a);
        
    }

    @Override
    public List<Persona> getPersonas(String bus) {
       List<Persona> personas = new ArrayList<>();
       personas.addAll(em.createNamedQuery("Persona.completar", Persona.class).setParameter("buscado", "%"+bus+"%").getResultList());
       return personas;
    }
    
}
