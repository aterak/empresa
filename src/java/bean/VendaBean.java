/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.ProdutoFacade;
import dao.VendaFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import modelo.Cliente;
import modelo.Produto;
import modelo.Venda;

/**
 *
 * @author EMPRESA
 */
@Named(value = "vendaBean")
@SessionScoped
public class VendaBean implements Serializable {

   
    private List<Venda> listaProdutosVendidos = new ArrayList();
    private List<Venda> carrinhoCompras = new ArrayList();
    private Venda venda = new Venda();
    private double valorTotal = 0;
    private int ultimaNota = 0;
    private int quantidadeItens = 0;//ok
    
  
    

    @EJB
    VendaFacade facadeVenda;

    public List<Venda> getListaProdutosVendidos() {
        listaProdutosVendidos = facadeVenda.findAll();

        return listaProdutosVendidos;
    }

    public List<Venda> getCarrinhoCompras() {
        return carrinhoCompras;
    }

    public void setCarrinhoCompras(List<Venda> carrinhoCompras) {

       // getListaProdutosVendidos();//invoco para rodar esta lista

        this.carrinhoCompras = carrinhoCompras;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getUltimaNota() {
        return ultimaNota;
    }

    public void setUltimaNota(int ultimaNota) {
        this.ultimaNota = ultimaNota;
    }

 

    
   
    
    public void incluirCarrinho(Produto prod) {
        int id = prod.getId();
        double valor = prod.getValor();
        String nome = prod.getNome();
        int quant = venda.getQuantidade();
        double totalprev = valor * quant;

        Venda v = new Venda();
        // v.setId(id);
        v.setValorvendaProdutoprevV(valor);
        v.setPrevProduto(nome);
        v.setQuantidade(quant);
        v.setTotalPrev(totalprev);
       
        
    
       
       

        carrinhoCompras.add(v);

        calculaTotal(); // calcula o total Geral

        venda.setQuantidade(null);

        CalcularUltimaNota();
    }

    public void realizarvenda() {
        Cliente c =new Cliente();
        
                 String nom= c.getNome();
        
        
        List<Venda> listaFinvenda = new ArrayList();
       
                                    
        for (Venda v2 : carrinhoCompras) {
            v2.setDataVenda(new Date());
            v2.setNotaFiscal(ultimaNota);
            v2.setValorTotal(valorTotal);
            v2.setPrevCliente(nom);
            listaFinvenda.add(v2);
        }
           for (Venda v3 : listaFinvenda) {
             facadeVenda.create(v3);
        }
        
       
        limparCarrinho();
        tamanho();
        calculaTotal();
        

       
    }

    public int tamanho() {

        quantidadeItens = 0;
        for (Venda v : carrinhoCompras) {
            quantidadeItens += v.getQuantidade();// assim da certo para ver o tamanho da lista
        }
        return quantidadeItens;

    }

    private void calculaTotal() {

        valorTotal = 0;

        for (Venda p : carrinhoCompras) {

            valorTotal += p.getTotalPrev();

        }
    }

    public void retirarCarrinho(Venda vend) {

        carrinhoCompras.remove(vend);

        calculaTotal();

    }

    public void limparCarrinho() {

        carrinhoCompras.removeAll(carrinhoCompras);

        calculaTotal();
        CalcularUltimaNota();
        
        venda.setValorTotal(null);
        venda.setNotaFiscal(null);
        venda.setQuantidade(null);
        ultimaNota= 0;
        
    }

    public int CalcularUltimaNota() {

        listaProdutosVendidos = facadeVenda.findAll();
         ultimaNota = 0;
        int maiorNota = 0;
        for (Venda l : listaProdutosVendidos) {// pego a ultima nota

            maiorNota = l.getNotaFiscal();

            if (maiorNota >= ultimaNota) {
                ultimaNota = maiorNota +1;
            }

        }
        return ultimaNota;
    }
 
    
    
    
    
    
    //depois apagar so para teste
    public void limparCarrinho2() {
   listaProdutosVendidos = facadeVenda.findAll();
     
     for (Venda v : listaProdutosVendidos) {
         
         facadeVenda.remove(v);
         
     }
     
  
 }
}
