/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Venda;

/**
 *
 * @author EMPRESA
 */
@Stateless
public class VendaFacade extends AbstractFacade<Venda> {
    @PersistenceContext(unitName = "ProjetoEmpresaJPAReportePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VendaFacade() {
        super(Venda.class);
    }
    public  List ultimanotafacade(){
    
    
           Query q = em.createNamedQuery("Venda.findAll");
          
            System.out.println("lista"+ q.getResultList());
            return q.getResultList();
             
               
               
        
    
    
      }
     
}
