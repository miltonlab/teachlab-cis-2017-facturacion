/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.modelo.entidades;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Persistence;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.OneToMany;
/**
 *
 * @author miltonlab
 */

@Entity
public class Factura implements java.io.Serializable{

    @Id
    private Integer numero;

    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private java.util.Date fecha;
  
    private Integer porcentaje_iva;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="cliente_id")
    private Cliente cliente = new Cliente();

    @OneToMany(mappedBy = "factura")
    private List<LineaFactura> lineas;

    public List<LineaFactura> getLineas() {
        return lineas;
    }

    public void setLineas(List<LineaFactura> lineas) {
        this.lineas = lineas;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Integer getPorcentaje_iva() {
        return porcentaje_iva;
    }

    public void setPorcentaje_iva(Integer porcentaje_iva) {
        this.porcentaje_iva = porcentaje_iva;
    }
    
    public java.util.Date getFecha() {
        return fecha;
    }

    public void setFecha(java.util.Date fecha) {
        this.fecha = fecha;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    
}
