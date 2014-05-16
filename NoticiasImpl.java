/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Ejb;

import Entidades.Noticia;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Emilio
 */

@Stateless
public class NoticiasImpl implements NoticiasEJB {
    
    @PersistenceContext(unitName = "HospitalEE-ejbPU")
    private EntityManager em;
    
    @Override
    public List<Noticia> allNoticias() {
        TypedQuery<Noticia> query = em.createNamedQuery("Noticias", Noticia.class);
        return query.getResultList();
    }
}
