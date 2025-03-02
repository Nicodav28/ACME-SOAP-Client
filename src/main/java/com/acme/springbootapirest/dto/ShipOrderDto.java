package com.acme.springbootapirest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ShipOrderDto {

    @NotNull
    @NotBlank
    @Size(min = 1, message = "numPedido no puede estar vacío")
    private String numPedido;

    @NotNull
    @NotBlank
    private String cantidadPedido;

    @NotNull
    @NotBlank
    private String codigoEAN;

    @NotNull
    @NotBlank
    private String nombreProducto;

    @NotNull
    @NotBlank
    private String numDocumento;

    @NotNull
    @NotBlank
    private String direccion;

    // Getters y Setters
    public String getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(String numPedido) {
        this.numPedido = numPedido;
    }

    public String getCantidadPedido() {
        return cantidadPedido;
    }

    public void setCantidadPedido(String cantidadPedido) {
        this.cantidadPedido = cantidadPedido;
    }

    public String getCodigoEAN() {
        return codigoEAN;
    }

    public void setCodigoEAN(String codigoEAN) {
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
        return "ShipOrderDto{" +
                "numPedido='" + numPedido + '\'' +
                ", cantidadPedido='" + cantidadPedido + '\'' +
                ", codigoEAN='" + codigoEAN + '\'' +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", numDocumento='" + numDocumento + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }

}