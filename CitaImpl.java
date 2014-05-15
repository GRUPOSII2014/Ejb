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
    
    public Trabajador trabajador(String nss){
        TypedQuery<Trabajador> trabajador = em.createNamedQuery("trabajador.get", Trabajador.class);
        Trabajador t=new Trabajador();
        for (Trabajador tr : trabajador.getResultList()){
            if(tr.getNumSegSocial().equals(nss)){
                t = tr;
                break;
            }
        }
        return t;
    }
    
    @Override
    public List<Cita> allCitas(String nss) {
        TypedQuery<Cita> query = em.createNamedQuery("cita.all", Cita.class);
        query.setParameter("trabajador", trabajador(nss));
        return query.getResultList();
    }

    @Override
    public List<Urgencia> allUrgencias(String nss) {
        TypedQuery<Urgencia> query = em.createNamedQuery("urgencia.all", Urgencia.class);
        query.setParameter("trabajador", trabajador(nss));
        return query.getResultList();
    }

    @Override
    public List<Cita> citasNoAtendidas(String nss) {
        TypedQuery<Cita> query = em.createNamedQuery("cita.noAtendias", Cita.class);
        query.setParameter("trabajador", trabajador(nss));
        return query.getResultList();
    }

    @Override
    public List<Cita> citasAtendidas(String nss) {
        TypedQuery<Cita> query = em.createNamedQuery("cita.atendidas", Cita.class);
        query.setParameter("trabajador", trabajador(nss));
        return query.getResultList();
    }

    @Override
    public List<Urgencia> urgenciasEspera(String nss) {
        TypedQuery<Urgencia> query = em.createNamedQuery("urgencia.all", Urgencia.class);
        query.setParameter("trabajador", trabajador(nss));
        List<Urgencia> urgencias = new ArrayList<>();
        for (Urgencia u : query.getResultList()) {
            if (u.getEstado().equals(u.getEstado().ESPERA)) {
                urgencias.add(u);
            }
        }
        return urgencias;
    }

    @Override
    public List<Urgencia> urgenciasAtendidas(String nss) {
        TypedQuery<Urgencia> query = em.createNamedQuery("urgencia.atendida", Urgencia.class);
        query.setParameter("trabajador", trabajador(nss));
        List<Urgencia> urgencias = new ArrayList<>();
        for (Urgencia u : query.getResultList()) {
            if (u.getEstado().equals(u.getEstado().ESPERA)) {
                urgencias.add(u);
            }
        }
        return urgencias;
    }

    @Override
    public void avanzaAtendiendo(Urgencia u) {
        u.setEstado(Enumerados.estadoUrgencia.ATENDIENDO);
        em.merge(u);
    }
    public void avanzaTratamiento(Urgencia u){
        u.setEstado(Enumerados.estadoUrgencia.TRATAMIENTO);
        em.merge(u);
    }
}
