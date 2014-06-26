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
import Entidades.Habitacion;
import Entidades.Persona;
import Entidades.Tratamiento;
import java.util.ArrayList;
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
    public void asignarCama(Persona p, Cama c){
        p.setCama(c);
        c.setPaciente(p);
        c.setEstado(Enumerados.estadoCama.OCUPADA);
        em.merge(c);
        em.merge(p);
    }
    
    @Override
    public Cama primeraLibre(Persona p){
        TypedQuery<Cama> query = em.createNamedQuery("Cama.all",Cama.class);
        List<Cama> camas= query.getResultList();
        
        String sexo = p.getSexo();
        
        for (Cama c:camas){
            if (puede(sexo,c))
                return c;
        }
        return null;
    }
    
    private boolean puede(String sexo, Cama c){
        if (c.getPaciente()!=null)
            return false;
        Habitacion h = c.getHabitacion();
        List<Cama> camas = new ArrayList<>();
        camas.addAll(h.getCamas());
        for (Cama c1:camas){
            Persona p = c1.getPaciente();
            if (p!=null && !p.getSexo().equals(sexo))
                return false;
        }
        
        return true;
        
    }
    
     @Override
    public List<Cama> todasCamasOcupadas() {
         TypedQuery<Cama> query = em.createNamedQuery("Cama.all", Cama.class);
         List<Cama> lista = new ArrayList<>();
         for (Cama c : query.getResultList()){
             if(c.getEstado()==Entidades.Enumerados.estadoCama.OCUPADA)
                 lista.add(c);
         }
        return lista;
    }
    
     @Override
    public void liberarCama(Integer c) {
        Persona p = em.createNamedQuery("Persona.nss", Persona.class).setParameter("nss", c).getSingleResult();
        Cama ca = p.getCama();
        p.setCama(null);
        ca.setEstado(Enumerados.estadoCama.LIBRE);
        ca.setPaciente(null);
        em.merge(ca);
        em.merge(p);
    }
    
    @Override
    public void terminarTratamiento(Tratamiento t){
        Persona p = t.getPersona();
        Entidades.Informe inf = new Entidades.Informe();
        inf.setObservaciones(t.getObservaciones());
        inf.setTipo(Entidades.Enumerados.tipoInforme.TRATAMIENTOS);
        inf.setTratamiento(t);
        Entidades.HistoriaClinica hist = p.getHistoriaclinica();
        List <Entidades.Informe> lista = hist.getInformes();
        lista.add(inf);
        hist.setInformes(lista);
        p.setHistoriaclinica(hist);
        em.merge(t);
        em.persist(inf);
        em.merge(hist);
        em.merge(p);
        
    }
    
    @Override
    public void crearCita(Cita c){
        em.persist(c);
    }

    @Override
    public List<Tratamiento> tratamientos(Integer nss) {
      return em.createNamedQuery("Tratemiento.persona", Tratamiento.class).setParameter("nss", nss).getResultList();
    }
}
