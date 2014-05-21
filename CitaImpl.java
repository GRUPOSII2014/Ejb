/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejb;

import Entidades.Cita;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author PyRoS
 */
@Stateless
public class CitaImpl implements CitaEjb {
    
    @PersistenceContext(unitName = "HospitalEE-ejbPU")
    private EntityManager em;
    
    @Override
    public List<Cita> citasNoAtendidas(Integer nss) {
        List <Cita> citas = new ArrayList<>();
        citas.addAll(em.createNamedQuery("cita.noAtendida",Cita.class).setParameter("nss", nss).getResultList());
        return citas;
    }

    @Override
    public void crearCita(Cita c) {
        em.persist(c);
    }

    @Override
    public List<Cita> citasDePersona(Integer nss) {
        ArrayList<Cita> citas = new ArrayList<>();
        citas.addAll(em.createNamedQuery("cita.noAsistida", Cita.class ).setParameter("nss", nss).getResultList());
        return citas;
    }

    @Override
    public int citasPorDelante(Integer nss) {
        ArrayList<Cita> citas = new ArrayList<>();
        citas.addAll(citasDePersona(nss));
        int i = -1;
        for(Cita c : citas){
            if(c.getTipoCita()==c.getTipoCita().LISTA_ESPERA){
                i++;
            }
        }
        return i;
    }

    @Override
    public void cancelarCita(Cita c) {
        c.setAtendido(true);
        em.merge(c);
    }

    @Override
    public void avanvaCita(Cita c) {
        c.setAtendido(true);
        em.merge(c);
    }

}
