/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Fornecedor;

/**
 *
 * @author EMPRESA
 */
@Stateless
public class FornecedorFacade extends AbstractFacade<Fornecedor> {
    @PersistenceContext(unitName = "ProjetoEmpresaJPAReportePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FornecedorFacade() {
        super(Fornecedor.class);
    }
    
}
