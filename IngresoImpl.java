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
