/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejb;

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
    public Error editarPersona(Persona p) {
        em.merge(p);
        return Error.NO_ERROR;
    }

    @Override
    public Error eliminarPersona(Persona p) {
        em.detach(p);
        if (em.contains(p)) {
            return Error.ERROR;
        } else {
            return Error.NO_ERROR;
        }
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
}
