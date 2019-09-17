/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Funcionario;

/**
 *
 * @author EMPRESA
 */
@Stateless
public class FuncionarioFacade extends AbstractFacade<Funcionario> {
    @PersistenceContext(unitName = "ProjetoEmpresaJPAReportePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FuncionarioFacade() {
        super(Funcionario.class);
    }
    
  public Boolean login(int id, String senha) {

        try {
            Funcionario u = em.createNamedQuery("funcionario.verLogin", Funcionario.class).setParameter("id", id).setParameter("senha", senha).getSingleResult();

            if (u != null) {
                System.out.println(id);
                System.out.println(senha);
                return true;
            }
            return false;
        } catch (Exception e) {
           new  Throwable( e.getMessage());
            return false;
        }
    }
    
    
}
