/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entityclases.Vendedor;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import sessionbeans.VendedorFacadeLocal;

/**
 *
 * @author luis
 */
@ManagedBean
@RequestScoped
public class AdministrarVendedoresManagedBean {
    
    /**
     * Creates a new instance of AdministrarVendedoresManagedBean
     */
    @EJB
    private VendedorFacadeLocal vendedorFacade;
    private List<Vendedor> vendedores;
    private String nombre, rut, gerenteNombre;

    public String getGerenteNombre() {
        return gerenteNombre;
    }

    public void setGerenteNombre(String gerenteNombre) {
        this.gerenteNombre = gerenteNombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }
    public List<Vendedor> getVendedores() {
        return vendedores;
    }

    public void setVendedores(List<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }
    public AdministrarVendedoresManagedBean() {
    }
    @PostConstruct
    public void inicializar(){
        vendedores = vendedorFacade.listarVendedores();
//        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext fc = FacesContext.getCurrentInstance();
//        fc.addMessage(rut, new FacesMessage("123123", "asdasdasdasd"));
        this.gerenteNombre = fc.getExternalContext().getRequestParameterMap().get("nombre");
        System.out.println(""+fc.getExternalContext().getRequestParameterMap().toString());
    }
    
    public void dialogNuevo(){
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('nuevoVendedor').show();");
    }
    
    public void nuevoVendedor(){
        this.vendedorFacade.crearVendedor(nombre, rut);
        this.inicializar();
        
    }
}
