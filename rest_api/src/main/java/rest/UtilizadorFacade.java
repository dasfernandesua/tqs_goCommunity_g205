/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import JPA.Campanha;
import JPA.CampanhaCreate;
import JPA.Utilizador;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author root
 */
@Stateless
public class UtilizadorFacade extends AbstractFacade<Utilizador> {

    @PersistenceContext(unitName = "PERSISTENCE_UNIT_NAME")
    private EntityManager em;

    public UtilizadorFacade() {
        super(Utilizador.class);
    }
    
    public Long create2(Utilizador u) {
        super.create(u);
        return u.getId();
    }
    
    public Utilizador login(UtilizadorREST.LoginXml login) {
        TypedQuery<Utilizador> query = em.createQuery("SELECT u FROM Utilizador u where u.email=\'"+login.email+"\'", Utilizador.class);
        List<Utilizador> lista=query.getResultList();
        if (lista.size()>0 ){
            if (lista.get(0).getPwhash().equals(login.pwhash)){
                return lista.get(0);
            }
        }
        return null;
    }

    public void edit(@PathParam("id") Long id, Utilizador entity) {
        super.edit(entity);
    }

    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    public Utilizador find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @Override
    public List<Utilizador> findAll() {
        return super.findAll();
    }

    public List<Utilizador> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
return em;
    }
}
