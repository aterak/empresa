/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.FuncionarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import modelo.Funcionario;

/**
 *
 * @author EMPRESA
 */
@Named(value = "funcionarioBean")
@SessionScoped
public class FuncionarioBean implements Serializable {

    /**
     * Creates a new instance of CilenteBen
     */
    
    
    private List<Funcionario> lista = new ArrayList();
    private Funcionario funcionario = new Funcionario();
     @EJB
    FuncionarioFacade facade;
    public FuncionarioBean() {
        
        
    }

    public Funcionario getFuncionario() {
        return funcionario ;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Funcionario> getLista() {
     
        return lista =  facade.findAll();
    }

    public String incluirFuncionario(){
   
    if(funcionario.getId()==null){
        
          facade.create(funcionario);
         
    }else
        
        facade.edit(funcionario);
     
        limparCampos();
      
    return "funcionario";
    
    } 
    
    public void salvar(){
    
    facade.create(funcionario);
    
    }
    
   public void alterar(){
   
   facade.edit(funcionario);
   
   }
     public String prepararEdita(Funcionario u) {

        funcionario = u;

       return "funcionario";

    }
   public void excluir(Funcionario u) {

        funcionario = u;
   facade.remove(funcionario);
   }
   
     public int tamanho() {

        //listaUsuarioBean = new UsuarioDao().getListaUsuario();
        return lista.size();// assim da certo para ver o tamanho da lista

    }
     
        public void limparCampos(){
     
        funcionario.setBairro(null);
       funcionario.setCel(null);
        funcionario.setCep(null);
        funcionario.setCidade(null);
        funcionario.setId(null);
        funcionario.setCpf(null);
        funcionario.setDataNasc(null);
        funcionario.setEstadocivil(null);
        funcionario.setEmail(null);
        funcionario.setEndereco(null);
        funcionario.setEstado(null);
        funcionario.setIdent(null);
        funcionario.setNome(null);
        funcionario.setSexo(null);
        funcionario.setTelCont(null);
        funcionario.setTel(null);
        
        
        
        }
   
}
