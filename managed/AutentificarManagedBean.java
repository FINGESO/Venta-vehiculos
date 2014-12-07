/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entityclases.Gerente;
import java.lang.ProcessBuilder.Redirect;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import sessionbeans.GerenteFacadeLocal;

/**
 *
 * @author luis
 */
@ManagedBean
@RequestScoped
public class AutentificarManagedBean {

    /**
     * Creates a new instance of AutentificarManagedBean
     */
    @EJB
    private GerenteFacadeLocal gerenteFacade;
    
    public String email, pass;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    public AutentificarManagedBean() {
        
    }
    
    public String autentificar(){
        String m = "";
        try{
            Gerente g = this.gerenteFacade.getGerente(email);
            if(g.getPass().equalsIgnoreCase(pass)){
                return "homeGerente?nombre="+g.getNombre()+"&amp;faces-redirect=true";
            }else{
                m = "Contraseña no coincide ";
            }
            
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", m);
            RequestContext.getCurrentInstance().showMessageInDialog(msg);
            System.out.println(m+email+" | "+pass+" | "+g.getNombre()+" | "+g.getRut());
        }catch(Exception e){
            System.out.println(""+e.toString());
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info","E-mail no encontrado");
        
            RequestContext.getCurrentInstance().showMessageInDialog(msg);
        }
        return "";
    }
    
    public void creargerente(){
        this.gerenteFacade.crearGerente();
    }
}
