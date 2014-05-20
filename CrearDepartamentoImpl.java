/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Ejb;

import Entidades.Departamento;
import Entidades.Habitacion;
import Entidades.Hospital;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ALBERTO
 */

@Stateless
public class CrearDepartamentoImpl implements CrearDepartamentoEjb{
    
    @PersistenceContext(unitName = "HospitalEE-ejbPU")
    private EntityManager em;

    @Override
    public void crearDepartamento(Departamento d) {
        em.persist(d);
    }

    @Override
    public List<Hospital> todosHospitales() {
        TypedQuery<Hospital> query = em.createNamedQuery("hospitales", Hospital.class);
        return query.getResultList();
    }
    
}
