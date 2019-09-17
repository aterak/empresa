/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.FornecedorFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import modelo.Fornecedor;

/**
 *
 * @author EMPRESA
 */
@Named(value = "fornecedorBean")
@SessionScoped
public class FornecedorBean implements Serializable {

    /**
     * Creates a new instance of CilenteBen
     */
    
    
    private List<Fornecedor> lista = new ArrayList();
    private Fornecedor fornecedor = new Fornecedor();
     @EJB
    FornecedorFacade facade;
    public FornecedorBean() {
        
        
    }

    public Fornecedor getFornecedor() {
        return fornecedor ;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<Fornecedor> getLista() {
     
        return lista =  facade.findAll();
    }

    public String incluirFornecedor() {
       
        if (fornecedor.getId() == null) {

            facade.create(fornecedor);
        } else {
            facade.edit(fornecedor);
        }

        limparCampos();

        return "fornecedor";

    }
    
    public void salvar(){
    
    facade.create(fornecedor);
    
    }
    
   public void alterar(){
   
   facade.edit(fornecedor);
   
   }
    
   public void excluir(){
   
   facade.remove(fornecedor);
   }

 public int tamanho() {

        //listaUsuarioBean = new UsuarioDao().getListaUsuario();
        return lista.size();// assim da certo para ver o tamanho da lista

    }

    public void limparCampos() {

        fornecedor.setBairro(null);
        fornecedor.setCel(null);
        fornecedor.setCep(null);
        fornecedor.setCgc(null);
        fornecedor.setCidade(null);
        fornecedor.setId(null); 
        fornecedor.setCpf(null);
        fornecedor.setDetalhe(null);
        fornecedor.setEmail(null);
        fornecedor.setEndereco(null);
        fornecedor.setEstado(null);
        fornecedor.setNome(null);
        fornecedor.setProduto(null);
        fornecedor.setTel(null);

    }



}
