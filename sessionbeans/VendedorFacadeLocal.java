/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entityclases.Vendedor;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author luis
 */
@Local
public interface VendedorFacadeLocal {

    void create(Vendedor vendedor);

    void edit(Vendedor vendedor);

    void remove(Vendedor vendedor);

    Vendedor find(Object id);

    List<Vendedor> findAll();

    List<Vendedor> findRange(int[] range);

    int count();

    Boolean crearVendedor(String nombre, String rut);

    List listarVendedores();
    
}
