package com.acme.springbootapirest.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "EnvioPedidoAcmeResponse", namespace = "http://WSDLs/EnvioPedidos/EnvioPedidosAcme")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderResponseAcme {
    //Since the last version of IntelliJ is not reading Lombok correctly and in consequence is not creating methods at compile time I choose to create my own
    //boilerplate methods
    @XmlElement(name = "EnvioPedidoResponse")
    private OrderResponseXML shipmentResponse;

    public OrderResponseXML getShipmentResponse() {
        return shipmentResponse;
    }

    public void setShipmentResponse(OrderResponseXML shipmentResponse) {
        this.shipmentResponse = shipmentResponse;
    }
}