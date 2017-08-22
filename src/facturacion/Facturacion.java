/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion;

import facturacion.controladores.FacturaJpaController;
import facturacion.controladores.LineaFacturaJpaController;
import facturacion.controladores.ProductoJpaController;
import facturacion.modelo.entidades.Cliente;
import facturacion.modelo.entidades.Factura;
import facturacion.modelo.entidades.LineaFactura;
import facturacion.modelo.entidades.Producto;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author miltonlab
 */
public class Facturacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FacturacionPU");
        Factura f = new Factura();
        f.setNumero(1001);
        f.setPorcentaje_iva(12);
        //f.setFecha(LocalDate.parse("2017-03-12"));
        f.setFecha(new java.util.Date());
        Cliente c = new Cliente();
        c.setCedula("1111111"); c.setNombres("Angel"); c.setApellidos("Baez");
        f.setCliente(c);
        FacturaJpaController controladorFactura = new FacturaJpaController(emf);
        try{
            controladorFactura.create(f);
            System.out.println("Factura Grabada");
        }catch(Exception e){
            System.out.println("error: " + e);
        }
        Producto p = new Producto();
        p.setCodigo("ABC-002");
        p.setDescripcion("Pintura");
        p.setPrecio(23.6);
        ProductoJpaController controladorProducto = new ProductoJpaController(emf);
        try{
            controladorProducto.create(p);
            System.out.println("Producto Grabado");
        }catch(Exception e){
            System.out.println("error al crear producto: " + e);
        }        
        LineaFactura lf = new LineaFactura();
        lf.setFactura(f);
        lf.setNumero(1200);
        lf.setProducto(p);
        LineaFacturaJpaController controladorLinea = new LineaFacturaJpaController(emf);
        try{
            controladorLinea.create(lf);
            System.out.println("Linea Factura Grabada");
        }catch(Exception e){
            System.out.println("error al crear linea factura: " + e);
        }        
    }
    
}
