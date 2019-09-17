/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.ClienteFacade;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.persistence.Query;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import modelo.Cliente;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;

/**
 *
 * @author EMPRESA
 */
@Named(value = "clienteBean")
@SessionScoped
public class ClienteBean implements Serializable {

    /**
     * Creates a new instance of ClienteBean
     */
    
    
    private List<Cliente> lista = new ArrayList();
    private Cliente cliente = new Cliente();
     @EJB
    ClienteFacade facade;
    public ClienteBean() {
        
        
    }

    public Cliente getCliente() {
        return cliente ;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getLista() {
     
        return lista =  facade.findAll();
    }

    public String incluirCliente() {
        
        if (cliente.getId() == null) {

            facade.create(cliente);
            limparCampos();
        } else {
            facade.edit(cliente);

            limparCampos();
        }
        limparCampos();
        return "cliente";
    }
    
    public void salvar(){
    
    facade.create(cliente);
    
    }
    
   public void alterar(){
   
   facade.edit(cliente);
     limparCampos();
   }
    
   public void excluir(Cliente u){
   this.cliente =u;
   facade.remove(cliente);
    limparCampos();
   }
   
   
   public String prepararEdita(Cliente u) {

        cliente = u;

        return "cliente";

    }
   public int tamanho() {

       
        return lista.size();// assim da certo para ver o tamanho da lista

    }

    public void limparCampos() {

        cliente.setBairro(null);
        cliente.setCel(null);
        cliente.setCep(null);
        cliente.setCidade(null);
        cliente.setId(null);
        cliente.setCpf(null);
        cliente.setDataNasc(null);
        cliente.setEstadocivil(null);
        cliente.setEmail(null);
        cliente.setEndereco(null);
        cliente.setEstado(null);
        cliente.setIdent(null);
        cliente.setNome(null);
        cliente.setSexo(null);
        cliente.setTel(null);
    }

    
    
    
      
    
public void exportarPDF(javax.faces.event.ActionEvent actionEvent) throws JRException, IOException{
Map<String,Object> paramters = new HashMap<String, Object>();
paramters.put("txtusuario","marcelo");

 File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Cliente.jasper"));
 JasperPrint imprime = JasperFillManager.fillReport(jasper.getPath(),paramters, new JRBeanCollectionDataSource(this.getLista()));

 HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
 httpServletResponse.addHeader("Content-disposition", "attachment; filename=relatorioCliente.pdf");  

        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();  
        JasperExportManager.exportReportToPdfStream(imprime, servletOutputStream);  
       
        servletOutputStream.flush();
        servletOutputStream.close();
        
        FacesContext.getCurrentInstance().responseComplete();  




}
public void exportarExcel(javax.faces.event.ActionEvent actionEvent) throws JRException, IOException{
Map<String,Object> paramters = new HashMap<String, Object>();
paramters.put("txtusuario","marcelo");

 File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Cliente.jasper"));
 JasperPrint imprime = JasperFillManager.fillReport(jasper.getPath(),paramters, new JRBeanCollectionDataSource(this.getLista()));

 HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
 httpServletResponse.addHeader("Content-disposition", "attachment; filename=relatorioCliente.xls");  

        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();  
       // JasperExportManager.exportReportToPdfStream(imprime, servletOutputStream);  
       
       
        
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, imprime);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        exporter.exportReport();
        
        servletOutputStream.flush();
        servletOutputStream.close();
        
        FacesContext.getCurrentInstance().responseComplete();  




}
public void exportarPPT(javax.faces.event.ActionEvent actionEvent) throws JRException, IOException{
Map<String,Object> paramters = new HashMap<String, Object>();
paramters.put("txtusuario","marcelo");

 File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Cliente.jasper"));
 JasperPrint imprime = JasperFillManager.fillReport(jasper.getPath(),paramters, new JRBeanCollectionDataSource(this.getLista()));

 HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
 httpServletResponse.addHeader("Content-disposition", "attachment; filename=relatorioCliente.ppt");  

        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();  
       // JasperExportManager.exportReportToPdfStream(imprime, servletOutputStream);  
       
       
        
        JRPptxExporter exporter = new JRPptxExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, imprime);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        exporter.exportReport();
        
        servletOutputStream.flush();
        servletOutputStream.close();
        
        FacesContext.getCurrentInstance().responseComplete();  




}
public void exportarDOC(javax.faces.event.ActionEvent actionEvent) throws JRException, IOException{
Map<String,Object> paramters = new HashMap<String, Object>();
paramters.put("txtusuario","marcelo");

 File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Cliente.jasper"));
 JasperPrint imprime = JasperFillManager.fillReport(jasper.getPath(),paramters, new JRBeanCollectionDataSource(this.getLista()));

 HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
 httpServletResponse.addHeader("Content-disposition", "attachment; filename=relatorioCliente.doc");  

        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();  
           
       
        
        JRDocxExporter exporter = new JRDocxExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, imprime);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        exporter.exportReport();
        
        servletOutputStream.flush();
        servletOutputStream.close();
        
        FacesContext.getCurrentInstance().responseComplete();  




}
public void exportarHTML(javax.faces.event.ActionEvent actionEvent) throws JRException, IOException{

 File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Cliente.jasper"));
 byte[] bite = JasperRunManager.runReportToPdf(jasper.getPath(), null,new JRBeanCollectionDataSource(this.getLista()));

 HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
 httpServletResponse.setContentType("application/pdf");
 httpServletResponse.setContentLength(bite.length);
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();  
       servletOutputStream.write(bite,0,bite.length);
       
       
        servletOutputStream.flush();
        servletOutputStream.close();
        
        FacesContext.getCurrentInstance().responseComplete();  




}
}

    
    
    
//public void pdf(ActionEvent actionEvent) throws JRException, IOException {  
//       
//         
//        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(this.getLista());  
//        //String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/report.jasper");  
//        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\c:\\rep\\relatorioCliente.jasper");  
//       
//        JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);  
//        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();  
//        httpServletResponse.addHeader("Content-disposition", "attachment; filename=relatorioCliente.pdf");  
//        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();  
//        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);  
//        FacesContext.getCurrentInstance().responseComplete();  
//    }  
//
//
//
//
//
//
//
//         JasperPrint jasperPrint;
//    public void init() throws JRException{
//        
//        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(getLista());
//       // jasperPrint = JasperFillManager.fillReport("src\\main\\java\\relatorio.jasper", new HashMap(),beanCollectionDataSource);
//       jasperPrint = JasperFillManager.fillReport("\\c:\\rep\\relatorioCliente.jasper", new HashMap(),beanCollectionDataSource);
//    
//    }
//    
//    public void PDF(ActionEvent actionEvent) throws IOException, JRException{
//    init();
//        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext();
//
//     //   httpServletResponse.addHeader("content-disposition", "attachment; filename= report.pdf");// isto serve para fazer download
//        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
//        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
//    
//    }
//    
//public  void relatorioGeralCliente(ActionEvent actionEvent) {
//
//        try {
//           
//            JasperReport reporter = JasperCompileManager.compileReport("\\c:\\rep\\relatorioCliente.jrxml");
//            // JasperReport reporter = JasperCompileManager.compileReport("usuarios.jrxml");
//            JasperPrint imprime = JasperFillManager.fillReport(reporter, null);
//            JasperViewer.viewReport(imprime, false);//o false serve para fechar apenas o relatorio
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//
//        }
//
//    }
//
//
//public void exportarPDF() throws JRException, IOException{
//Map<String,Object> paramters = new HashMap<String, Object>();
////paramters.put();
//
// File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\c:\\rep\\relatorioCliente.jrxml"));
// JasperPrint imprime = JasperFillManager.fillReport(jasper.getPath(),null, new JRBeanCollectionDataSource(this.lista));
//
// HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
// httpServletResponse.addHeader("Content-disposition", "attachment; filename=relatorioCliente.pdf");  
//
//        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();  
//        JasperExportManager.exportReportToPdfStream(imprime, servletOutputStream);  
//       
//        servletOutputStream.flush();
//        servletOutputStream.close();
//        
//        FacesContext.getCurrentInstance().responseComplete();  
//
//
//
//
//}
//
//
//         JasperPrint jasperPrint2;
//    public void init2() throws JRException{
//        
//        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(lista);
//      String reportPacth = (FacesContext.getCurrentInstance().getExternalContext().getRealPath("./relatorioCliente.jasper"));
//      jasperPrint2 = JasperFillManager.fillReport(reportPacth, new HashMap(),beanCollectionDataSource);
//    
//    }
//    
//    public void PDF2() throws IOException, JRException{
//        init2();
//        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext();
//
//       httpServletResponse.addHeader("content-disposition", "attachment; filename= relatorioCliente.pdf");// isto serve para fazer download
//        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
//        JasperExportManager.exportReportToPdfStream(jasperPrint2, servletOutputStream);
//       FacesContext.getCurrentInstance().responseComplete(); 
//    }

    
    

