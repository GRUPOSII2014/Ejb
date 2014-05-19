/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejb;

import Entidades.Alerta;
import Entidades.Contacto;
import Entidades.HistoriaClinica;
import Entidades.Mensaje;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author PyRoS
 */
import Entidades.Persona;
import Entidades.Trabajador;
import java.util.Date;
import javax.persistence.NoResultException;

@Stateless
public class PersonaImpl implements PersonaEjb {

    @PersistenceContext(unitName = "HospitalEE-ejbPU")
    private EntityManager em;

    @Override
    public void crearPersona(Persona p) {
        em.persist(p);
    }

    @Override
    public List<Persona> todasPersonas() {
        TypedQuery<Persona> query = em.createNamedQuery("Persona.all", Persona.class);
        return query.getResultList();
    }

    @Override
    public Persona compruebaPersona(Integer nss, String passwd) {
        Persona p;

        try {
            p = em.createNamedQuery("Login.Comprueba", Persona.class)
                    .setParameter("nss", nss)
                    .setParameter("passwd", passwd)
                    .getSingleResult();
        } catch (NoResultException ex) {
            p = null;
        }

        return p;
    }

    @Override
    public Persona getPersona(Integer nss) {
        return em.createNamedQuery("Persona", Persona.class)
                .setParameter("nss", nss)
                .getSingleResult();
    }

    @Override
    public HistoriaClinica getHistoria(Integer nss) {
        return getPersona(nss).getHistoriaclinica();
    }
    
    @Override
    public List<Alerta> allAlertas(Integer nss) {
        return em.createNamedQuery("listaAlertas", Alerta.class)
                .setParameter("nss", nss)
                .setParameter("hora", new Date())
                .getResultList();
    }
    
    @Override
    public List<Mensaje> allMensajes(Integer nss) {
        Trabajador t = em.createQuery("select t from Trabajador t where t.numSegSocial = :nss", Trabajador.class)
                .setParameter("nss", nss)
                .getSingleResult();
        return em.createNamedQuery("listaMensajes", Mensaje.class)
                .setParameter("nss", t)
                .getResultList();
    }

    @Override
    public void crearFormularioContacto(Contacto c) {
        em.persist(c);
    }

    @Override
    public Error actualizaPersona(Persona p) {
       em.merge(p);
       return Error.NO_ERROR;
    }

    @Override
    public Trabajador getTrabajador(Integer nss) {
        return em.createQuery("select t from Trabajador t where t.numSegSocial = :nss", Trabajador.class)
                .setParameter("nss", nss)
                .getSingleResult();
    }
    
}
