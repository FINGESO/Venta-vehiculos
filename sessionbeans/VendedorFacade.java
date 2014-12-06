/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entityclases.Vendedor;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author luis
 */
@Stateless
public class VendedorFacade extends AbstractFacade<Vendedor> implements VendedorFacadeLocal {
    @PersistenceContext(unitName = "VentaAutos-ejbPU")
    private EntityManager em;

    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VendedorFacade() {
        super(Vendedor.class);
    }

    @Override
    public Boolean crearVendedor(String nombre, String rut) {
        System.out.println("Creando Vendedor");
        Vendedor v = new Vendedor();
        v.setNombre(nombre);
        v.setRut(rut);
        try{
            this.getEntityManager().persist(v);
            return true;
        }catch(Exception e){
            System.out.println(e.toString());
            return false;
        }
        
    }
    
}
