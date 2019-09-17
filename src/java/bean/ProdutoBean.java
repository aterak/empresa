/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.ProdutoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import modelo.Produto;

/**
 *
 * @author EMPRESA
 */
@Named(value = "produtoBean")
@SessionScoped
public class ProdutoBean implements Serializable {

    /**
     * Creates a new instance of CilenteBen
     */
    
    
    private List<Produto> lista = new ArrayList();
    private List<Produto> lista2 = new ArrayList();
    private Produto produto = new Produto();
     @EJB
    ProdutoFacade facade;
    public ProdutoBean() {
        
        
    }

    public Produto getProduto() {
        return produto ;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getLista() {
     
        return lista =  facade.findAll();
    }

    public List<Produto> getLista2() {
         lista2 =  facade.findAll(); 
        
         List<Produto> listado = new ArrayList();
         Produto p1 = new Produto();
         for (Produto p2 : lista2) {
            
             int i = p2.getId();
             
                  p1.setId( i);
             
         }
        listado.add(p1);
         return listado;
    }

    
    
    
    
    
      public String incluirProduto() {
       
        if (produto.getId() == null) {

           facade.create(produto);
            limparCampos();
        } else {
             facade.edit(produto);
            limparCampos();
        }

        limparCampos();

       return "produto";

    }
    
    public void salvar(){
    
    facade.create(produto);
    
    }
    
   public void alterar(){
   
   facade.edit(produto);
   
   }
    
   public void excluir(){
   
   facade.remove(produto);
   }
   
    public int tamanho() {

        //listaUsuarioBean = new UsuarioDao().getListaUsuario();
        return lista.size();// assim da certo para ver o tamanho da lista

    }

    public void limparCampos() {

        produto.setId(null);
        produto.setCodigoInter(null);
        produto.setFornecedor(null);
        produto.setNome(null);
        produto.setValor(null);
        produto.setPcusto(null);
        produto.setEstoqueMinimo(null);
        produto.setQuant(null);
    }

}