/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Prevvenda;

/**
 *
 * @author EMPRESA
 */
@Stateless
public class PrevvendaFacade extends AbstractFacade<Prevvenda> {
    @PersistenceContext(unitName = "ProjetoEmpresaJPAReportePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrevvendaFacade() {
        super(Prevvenda.class);
    }
    
}
