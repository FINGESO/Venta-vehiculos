/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entityclases.Gerente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author luis
 */
@Stateless
public class GerenteFacade extends AbstractFacade<Gerente> implements GerenteFacadeLocal {
    @PersistenceContext(unitName = "VentaAutos-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GerenteFacade() {
        super(Gerente.class);
    }

    @Override
    public Gerente getGerente(String email) {
        Gerente ger = null;
        System.out.println("Buscando a :"+email);
        try{
            ger = Gerente.class.cast(getEntityManager().createQuery("SELECT c FROM Gerente c WHERE c.correo LIKE :correoo")
                .setParameter("correoo", email)
                .getResultList().get(0));
            System.out.println("Se encontró a :"+ger.getNombre()+" "+ger.getRut());
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }catch (NoResultException e){
            System.out.println("No se encontró nada");
            return null;
        }catch (NonUniqueResultException e){
            System.out.println("Se encontró más de un cliente");
            return null;
        }
        return ger;
    }

    @Override
    public void crearGerente() {
        
        System.out.println("Creando Vendedor");
        Gerente v = new Gerente();
        v.setCorreo("juanp@venta.cl");
        v.setNombre("Juan Perez");
        v.setPass("1234");
        v.setRut("9548652-4");
        try{
            this.getEntityManager().persist(v);
            return;
        }catch(Exception e){
            System.out.println(e.toString());
            return;
        }
        
    }
    
    
}
