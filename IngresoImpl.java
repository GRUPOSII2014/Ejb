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
import Entidades.Urgencia;
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
    }
    
    @Override
    public Cama primeraLibre(){
        TypedQuery<Cama> query = em.createNamedQuery("Cama.all",Cama.class);
        List<Cama> camas= query.getResultList();
        
        return camas.get(0);
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
    }
    
    @Override
    public void crearCita(Cita c){
        em.persist(c);
    }
    
    @Override
    public void crearUrgencia(Urgencia u){
        em.persist(u);
    }
}
