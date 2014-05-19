/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejb;

import Entidades.Cita;
import Entidades.Trabajador;
import Entidades.Urgencia;
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
    public List<Cita> allCitas(Trabajador t) {
        TypedQuery<Cita> query = em.createNamedQuery("cita.all", Cita.class);
        query.setParameter("trabajador", t);
        return query.getResultList();
    }

    @Override
    public List<Urgencia> allUrgencias(Trabajador t) {
        TypedQuery<Urgencia> query = em.createNamedQuery("urgencia.all", Urgencia.class);
        query.setParameter("trabajador", t);
        return query.getResultList();
    }

    @Override
    public List<Cita> citasNoAtendidas(Trabajador t) {
        TypedQuery<Cita> query = em.createNamedQuery("cita.noAtendias", Cita.class);
        query.setParameter("trabajador", t);
        return query.getResultList();
    }

    @Override
    public List<Cita> citasAtendidas(Trabajador t) {
        TypedQuery<Cita> query = em.createNamedQuery("cita.atendidas", Cita.class);
        query.setParameter("trabajador", t);
        return query.getResultList();
    }

    @Override
    public List<Urgencia> urgenciasEspera(Trabajador t) {
        TypedQuery<Urgencia> query = em.createNamedQuery("urgencia.espera", Urgencia.class);
        query.setParameter("trabajador", t);
        return query.getResultList();
    }

    @Override
    public List<Urgencia> urgenciasAtendidas(Trabajador t) {
        TypedQuery<Urgencia> query = em.createNamedQuery("urgencia.atendida", Urgencia.class);
        query.setParameter("trabajador", t);
        return query.getResultList();
    }

}
