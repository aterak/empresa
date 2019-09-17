/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author EMPRESA
 */
@Entity
@Table(name = "prevvenda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prevvenda.findAll", query = "SELECT p FROM Prevvenda p"),
    @NamedQuery(name = "Prevvenda.findById", query = "SELECT p FROM Prevvenda p WHERE p.id = :id"),
    @NamedQuery(name = "Prevvenda.findByDataVenda", query = "SELECT p FROM Prevvenda p WHERE p.dataVenda = :dataVenda"),
    @NamedQuery(name = "Prevvenda.findByNotaFiscal", query = "SELECT p FROM Prevvenda p WHERE p.notaFiscal = :notaFiscal"),
    @NamedQuery(name = "Prevvenda.findByPrevCliente", query = "SELECT p FROM Prevvenda p WHERE p.prevCliente = :prevCliente"),
    @NamedQuery(name = "Prevvenda.findByPrevProduto", query = "SELECT p FROM Prevvenda p WHERE p.prevProduto = :prevProduto"),
    @NamedQuery(name = "Prevvenda.findByPrevVendedor", query = "SELECT p FROM Prevvenda p WHERE p.prevVendedor = :prevVendedor"),
    @NamedQuery(name = "Prevvenda.findByQuantidade", query = "SELECT p FROM Prevvenda p WHERE p.quantidade = :quantidade"),
    @NamedQuery(name = "Prevvenda.findByTotalPrev", query = "SELECT p FROM Prevvenda p WHERE p.totalPrev = :totalPrev"),
    @NamedQuery(name = "Prevvenda.findByTroco", query = "SELECT p FROM Prevvenda p WHERE p.troco = :troco"),
    @NamedQuery(name = "Prevvenda.findByValorPago", query = "SELECT p FROM Prevvenda p WHERE p.valorPago = :valorPago"),
    @NamedQuery(name = "Prevvenda.findByValorTotal", query = "SELECT p FROM Prevvenda p WHERE p.valorTotal = :valorTotal"),
    @NamedQuery(name = "Prevvenda.findByValorvendaProdutoprevV", query = "SELECT p FROM Prevvenda p WHERE p.valorvendaProdutoprevV = :valorvendaProdutoprevV")})
public class Prevvenda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_venda")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataVenda;
    @Column(name = "nota_fiscal")
    private Integer notaFiscal;
    @Size(max = 255)
    @Column(name = "prevCliente")
    private String prevCliente;
    @Size(max = 255)
    @Column(name = "prevProduto")
    private String prevProduto;
    @Size(max = 255)
    @Column(name = "prevVendedor")
    private String prevVendedor;
    @Column(name = "quantidade")
    private Integer quantidade;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "totalPrev")
    private Double totalPrev;
    @Column(name = "troco")
    private Double troco;
    @Column(name = "valor_pago")
    private Double valorPago;
    @Column(name = "valor_total")
    private Double valorTotal;
    @Column(name = "valor_venda_Produto_prevV")
    private Double valorvendaProdutoprevV;

    public Prevvenda() {
    }

    public Prevvenda(Integer id) {
        this.id = id;
    }

    public Prevvenda(Integer id, Date dataVenda) {
        this.id = id;
        this.dataVenda = dataVenda;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Integer getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(Integer notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public String getPrevCliente() {
        return prevCliente;
    }

    public void setPrevCliente(String prevCliente) {
        this.prevCliente = prevCliente;
    }

    public String getPrevProduto() {
        return prevProduto;
    }

    public void setPrevProduto(String prevProduto) {
        this.prevProduto = prevProduto;
    }

    public String getPrevVendedor() {
        return prevVendedor;
    }

    public void setPrevVendedor(String prevVendedor) {
        this.prevVendedor = prevVendedor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getTotalPrev() {
        return totalPrev;
    }

    public void setTotalPrev(Double totalPrev) {
        this.totalPrev = totalPrev;
    }

    public Double getTroco() {
        return troco;
    }

    public void setTroco(Double troco) {
        this.troco = troco;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getValorvendaProdutoprevV() {
        return valorvendaProdutoprevV;
    }

    public void setValorvendaProdutoprevV(Double valorvendaProdutoprevV) {
        this.valorvendaProdutoprevV = valorvendaProdutoprevV;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prevvenda)) {
            return false;
        }
        Prevvenda other = (Prevvenda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Prevvenda[ id=" + id + " ]";
    }
    
}
