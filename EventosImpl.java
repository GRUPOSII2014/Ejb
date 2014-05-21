/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Ejb;

import Entidades.Alerta;
import Entidades.Persona;
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
    public void crearEventos(Alerta a, Persona p) {
        if (!em.contains(p))
            em.merge(p);
        
        if (p.getAlertas() == null)
            p.setAlertas(new ArrayList<Alerta>());
        
        p.getAlertas().add(a);
        em.persist(a);
        em.merge(p);
    }
    
    @Override
    public void crearEventos(Alerta a) {
        List<Persona> lista = ejb.todasPersonas();
        
        for (Persona p : lista) {
            if (p.getAlertas() == null)
                p.setAlertas(new ArrayList<Alerta>());
            
            p.getAlertas().add(a);
        }
       
        em.persist(a);
        
        for (Persona p : lista) {
            em.merge(p);
        }
    }

    @Override
    public List<Persona> getPersonas(String bus) {
       List<Persona> personas = new ArrayList<>();
       personas.addAll(em.createNamedQuery("Persona.completar", Persona.class).setParameter("buscado", "%"+bus+"%").getResultList());
       return personas;
    }
    
}
