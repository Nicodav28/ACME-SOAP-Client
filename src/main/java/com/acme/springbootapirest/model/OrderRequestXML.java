package com.acme.springbootapirest.model;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "EnvioPedidoAcme", namespace = "http://WSDLs/EnvioPedidos/EnvioPedidosAcme")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderRequestXML {

    @XmlElementWrapper(name = "EnvioPedidoRequest") // Contenedor de la lista
    @XmlElement(name = "Pedido") // Nombre de cada elemento dentro de la lista
    private List<OrdersXML> orders = new ArrayList<>();

    public List<OrdersXML> getOrders() {
        return orders;
    }

    public void setOrders(List<OrdersXML> orders) {
        this.orders = orders;
    }

    public void addOrder(OrdersXML order) {
        this.orders.add(order);
    }

    @Override
    public String toString() {
        return "OrderRequestXML{" +
                "orders=" + orders +
                '}';
    }
}
