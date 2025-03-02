package com.acme.springbootapirest.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.math.BigInteger;

@XmlRootElement(name = "EnvioPedidoRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrdersXML {

    @XmlElement(name = "pedido")
    private String numPedido;

    @XmlElement(name = "Cantidad")
    private Integer cantidadPedido;

    @XmlElement(name = "EAN")
    private BigInteger codigoEAN;

    @XmlElement(name = "Producto")
    private String nombreProducto;

    @XmlElement(name = "Cedula")
    private String numDocumento;

    @XmlElement(name = "Direccion")
    private String direccion;

    // Getters y Setters
    public String getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(String numPedido) {
        this.numPedido = numPedido;
    }

    public Integer getCantidadPedido() {
        return cantidadPedido;
    }

    public void setCantidadPedido(Integer cantidadPedido) {
        this.cantidadPedido = cantidadPedido;
    }

    public BigInteger getCodigoEAN() {
        return codigoEAN;
    }

    public void setCodigoEAN(BigInteger codigoEAN) {
        this.codigoEAN = codigoEAN;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "OrdersXML{" +
                "numPedido='" + numPedido + '\'' +
                ", cantidadPedido=" + cantidadPedido +
                ", codigoEAN=" + codigoEAN +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", numDocumento='" + numDocumento + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
