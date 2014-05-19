/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejb;

import Entidades.Cama;
import Entidades.Cantidad;
import Entidades.Cita;
import Entidades.Enumerados;
import Entidades.Persona;
import Entidades.Tratamiento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Fernando
 */
@Stateless
public class IngresoImpl implements IngresoEjb {

    @PersistenceContext(unitName = "HospitalEE-ejbPU")
    private EntityManager em;

    @Override
    public Persona getPersona(Integer nss) {
      Persona p = em.find(Persona.class, nss);
      return p;
      //  return em.createNamedQuery("Persona", Persona.class).
        //        setParameter("nss", nss).getSingleResult();
    }
    
    @Override
    public void asignarCama(Persona p, Cama c){
        p.setCama(c);
        c.setPaciente(p);
        c.setEstado(Enumerados.estadoCama.OCUPADA);
    }
    
    @Override
    public Cama primeraLibre(){
        TypedQuery<Cama> query = em.createNamedQuery("Cama.all",Cama.class);
        List<Cama> camas = query.getResultList();
        return camas.get(0);
    }
    
    @Override
    public void terminarTratamiento(Tratamiento t){
        Persona p = t.getPersona();
        List<Tratamiento> tratamientos = p.getTratamiento();
        tratamientos.remove(t);
        List<Cantidad> cantidades = t.getCantidades();
        for (Cantidad c:cantidades){
            em.remove(c);
        }
        em.remove(t);
    }
    
    @Override
    public void crearCita(Persona p, Cita c){
        em.persist(c);
        List<Cita> citas = p.getCitas();
        citas.add(c);
        c.setPersona(p);
    }
}
