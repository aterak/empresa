/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;


import dao.FuncionarioFacade;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Funcionario;

/**
 *
 * @author EMPRESA
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    /**
     * Creates a new instance of LoginBean
     */
    private Funcionario funcionario;
    
    @EJB
    FuncionarioFacade facade;
    
    public LoginBean() {
    }
    
    public Funcionario getFuncionario() {
       
        if(funcionario == null){
        
        funcionario = new Funcionario();
        }
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    
    
    
    public String verificaLogin() {
       
        if (facade.login(funcionario.getId(), funcionario.getSenha())) {

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nome", funcionario.getId());

            return "cliente";
            //"home.xhtml?faces-redirect=true";
         
    } else {
   
      return "login";
      }
      }   
    
    public Boolean verificaSessao() {
        Boolean estado = false;
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nome") == null) {

            estado = false;
        } else {
            estado = true;
        }
        return estado;
    }
    
 public String encerrarSessao() {

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login?faces-redirect=true";
        // ou apenas login
    }
 
 
 public String mensagemBoasVindas(ActionEvent actionEvent) {
       
        String retorno ;
        Calendar c1 = Calendar.getInstance();
        int hora = c1.get(Calendar.HOUR_OF_DAY);
        if (hora <= 12) {
       return     retorno =("Bom dia");
           //  System.out.println(retorno);
        } else if (hora > 12 && hora < 18) {
      return     retorno=("Boa tarde");
 //System.out.println(retorno);
        } else {
      return  retorno=("Boa noite");
         //   System.out.println(retorno);
        }
        
    }
 
}
