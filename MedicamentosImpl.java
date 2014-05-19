/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Ejb;

import Entidades.Medicamento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
/**
 *
 * @author Ricardo
 */

@Stateless
public class MedicamentosImpl implements MedicamentosEjb {

     @PersistenceContext(unitName = "HospitalEE-ejbPU")
    private EntityManager em;
    
    @Override
    public List<Medicamento> getMedicamento() {
        TypedQuery<Medicamento> query = em.createNamedQuery("Medicamento.consulta", Medicamento.class);
        return query.getResultList();    
    }
   
}
