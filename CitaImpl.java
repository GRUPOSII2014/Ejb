/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejb;

import Entidades.Cita;
import Entidades.Enumerados;
import Entidades.Trabajador;
import Entidades.Urgencia;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author PyRoS
 */
@Stateless
public class CitaImpl implements CitaEjb {
    
    @PersistenceContext(unitName = "HospitalEE-ejbPU")
    private EntityManager em;

    @Override
    public List<Urgencia> urgenciasEspera(Integer nss) {
        List<Urgencia> urgencias = new ArrayList<>();
        for(Urgencia u : em.createNamedQuery("urgencia.trabajador", Urgencia.class).setParameter("nss", nss).getResultList()){
            if(u.getEstado() == u.getEstado().ESPERA) urgencias.add(u);
        }
        return urgencias;
    }

    @Override
    public void avanzaAtendiendo(Urgencia u) {
        u.setEstado(Enumerados.estadoUrgencia.ATENDIENDO);
        em.merge(u);
    }
    
    @Override
    public void avanzaTratamiento(Urgencia u){
        u.setEstado(Enumerados.estadoUrgencia.TRATAMIENTO);
        em.merge(u);
    }

    @Override
    public List<Cita> citasNoAtendidas(Integer nss) {
        List<Cita> citas = new ArrayList<>();
        List<Cita> c2 = em.createNamedQuery("cita.trabajador", Cita.class).setParameter("nss", nss).getResultList();
        for(Cita c : c2){
            if(!c.isAtendido()) citas.add(c);
        }
        return citas;
    }
}
