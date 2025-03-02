package com.acme.springbootapirest.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "EnvioPedidoResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderResponseXML {
    //Since the last version of IntelliJ is not reading Lombok correctly and in consequence is not creating methods at compile time I choose to create my own
    //boilerplate methods
    @XmlElement(name = "Codigo")
    private String shippingId;
    @XmlElement(name = "Mensaje")
    private String shippingStatus;

    public String getShippingId() {
        return shippingId;
    }

    public String getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingId(String shippingId) {
        this.shippingId = shippingId;
    }

    public void setShippingStatus(String shippingStatus) {
        this.shippingStatus = shippingStatus;
    }
}